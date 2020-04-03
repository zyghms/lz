package com.zygh.lz.vo;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j2
public class MapperAspect {


    @AfterReturning("execution(* com.zygh.lz.*mapper.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("=============");
        log.info("Completed: " + joinPoint);
    }


    /**
     * 监控com.lsj.xcjfs.dao..*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* com.zygh.lz.*mapper.*(..))")
    private void pointCutMethod() {
        System.out.println("=============");
    }

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();
        System.out.println("=============");
        log.info("调用Mapper方法：{}，参数：{}，执行耗时：{}纳秒，耗时：{}毫秒",
                pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()),
                (end - begin), (end - begin) / 1000000);
        return obj;
    }
}