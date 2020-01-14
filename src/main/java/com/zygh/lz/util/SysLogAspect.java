package com.zygh.lz.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 系统日志：切面处理类
 */
@Aspect
@Component
public class SysLogAspect/* implements Filter*/ {

    private static final Logger log = LoggerFactory.getLogger(SysLogAspect.class);

    /*@Autowired
    private IOperationService operationService;*/

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.zygh.lz.util.ViLog)")
    public void logPoinCut() {
    }


    //切面 配置通知
    @AfterReturning("logPoinCut()")         //AfterReturning
    public void saveOperation(JoinPoint joinPoint) throws IOException, ServletException {
        log.info("---------------接口日志记录---------------");
        //保存日志
        Operation operation = new Operation();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        System.out.println("signature="+signature);
        //获取切入点所在的方法
        Method method = signature.getMethod();
//        System.out.println("method="+method);

        //获取操作--方法上的ViLog的值
        ViLog viLog = method.getAnnotation(ViLog.class);
        if (viLog != null) {
            //保存操作事件、日志类型
            String logType = viLog.logType();
            operation.setLogType(logType);

            //功能模块
            String module = viLog.module();
            operation.setModule(module);

        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String httpMethod = request.getMethod();
        /*//操作的url
        String requestURI = request.getRequestURI();
        String requestURL = request.getRequestURL().toString();
        operation.setOperUrl(requestURL);*/
        // 客户端ip
        String ip = request.getRemoteAddr();
        operation.setTerminalIp(ip);
        if (request.getAttribute("policeNum") == null || request.getAttribute("policeNum").equals("")) {
            // 操作人警员号
            //String policeNum = SecurityContextHolder.getContext().getAuthentication().getName();

            operation.setPoliceId("警员号为空");
        } else {
            operation.setPoliceId((String) request.getAttribute("policeNum"));
        }

        //浏览器唯一标识
        String sessionId = request.getSession().getId();
        operation.setSessionId(sessionId);

        //请求参数信息
        String paramter = "";
        //get的参数
        if (httpMethod.equalsIgnoreCase("GET")) {
            Object[] args = joinPoint.getArgs();
            Object[] arguments = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                    //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                    //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                    continue;
                }

                arguments[i] = args[i];
            }

            if (arguments != null) {
                try {
                    paramter = JSONObject.toJSONString(arguments);
                } catch (Exception e) {
                    paramter = arguments.toString();
                }
            }
            operation.setFormatParam(paramter);
            //其他方法的参数
        } else {
            String param = "";
            try {
                request.setCharacterEncoding("UTF-8");
                if (request.getMethod().equalsIgnoreCase("post")) {
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();
                    String inputStr;
                    while ((inputStr = streamReader.readLine()) != null)
                        responseStrBuilder.append(inputStr);

                    JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
                    param = jsonObject.toJSONString();
                }
            } catch (IOException e) {
                log.error("error：" + e);
            }
            operation.setFormatParam(param);
        }

        //响应数据
        String result = JSON.toJSONString(request.getAttribute("result")) ;
        System.out.println("result:"+result);
        operation.setResponse(result);

        operation.setResponseType("1");
        try {
            Map<String, String> map = (Map<String, String>) JSON.parse(result);
            if (String.valueOf(map.get("code")).equals("1") || String.valueOf(map.get("code")).equals("200")) {
                operation.setResult("成功");
                operation.setErrorCode("");
                operation.setErrorLog("");
            } else {
                operation.setResult("失败");
                operation.setErrorCode(map.get("code") + "");
                operation.setErrorLog(map.get("msg"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            operation.setResult("成功");
            operation.setErrorCode("");
            operation.setErrorLog("");
        }

        System.out.println("result:" + operation);
        try {
            SendLogUtils.sendLog(operation);
            System.out.println("================发送日志成功================");
        } catch (Exception e) {
            e.printStackTrace();
            //发送日志失败
            System.out.println("------发送日志失败--------");
        }
//        String contentType = response.getContentType();

        /*ResponseWrapper servletResponse = new ResponseWrapper(response);
        System.out.println(servletResponse.toString());
        String result = servletResponse.getResult();
        System.out.println(result);*/

/*        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        System.out.println("method name="+className + "." + methodName);*/
    }

    /*@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
        System.out.println(responseWrapper.getResult());
        System.out.println(response.toString());
    }*/
}
