package com.zygh.lz.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.zygh.lz.entity.Cfparticulars;
import com.zygh.lz.entity.ChangFeng;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 对接长峰科技第三方接口类
 */
@RestController
public class CommonFunForHttp {

    //获取是否签到签退信息
    @GetMapping("findRealTime")
    public String findRealTime(String staffnum) {
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        String result1 = HttpUtil.get("http://62.64.22.153:10002/biz_police_duty/api/findQwRwxxOnDutyByUserId/" + staffnum);
        return result1;
    }

    /**
     * 创建顶层任务接口
     * <p>
     * "cjrdw": "410100000000",            创建人单位 默认给市局机构代码410100000000
     * "pid": "-1",                       pid为-1的时候创建顶级勤务
     * "px": 1,                           排序字段
     * "rwzmc": "创建顶层任务测试",          任务组名称
     * "rwzms": "pid为-1时创建顶层任务"      任务组描述
     */
    @PostMapping("foundTop")
    public ResultBean foundTop(ChangFeng changFeng) {
        Map<String, Object> param = new HashMap<>();
        String url = "http://62.64.22.153:10002/biz_police_duty/api/createQwRwfzb";
        param.put("cjrdw", "410100000000");
        param.put("pid", "-1");
        param.put("px", "1");
        param.put("rwzmc", changFeng.getRwzmc());
        param.put("rwzms", changFeng.getRwzms());
        //String s = HttpUtil.post(url, param);
        String  paramString= JSON.toJSONString(param);
        String result = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("X-Bmob-Application-Id","2f0419a31f9casdfdsf431f6cd297fdd3e28fds4af")
                .header("X-Bmob-REST-API-Key","1e03efdas82178723afdsafsda4be0f305def6708cc6")
                .body(paramString)
                .execute().body();
        System.out.println("创建顶级任务:" + result);
        return ResultUtil.setOK("success", result);
    }

    /**
     * 创建任务分组分组实例
     * <p>
     * "cjrdw": "410100000000",                             创建人单位 默认给市局机构代码410100000000
     * "pid": "0d6965e88bee46658197aeb0f71bb850",          pid为上层任务生成的唯一标识
     * "px": 1,                                            排序字段
     * "rwzmc": "创建任务分组测试",                           任务组名称
     * "rwzms": "创建任务分组时需填写所属任务的pid"             任务组描述
     */
    @PostMapping("createTask")
    public ResultBean createTask(ChangFeng changFeng) {
        Map<String, Object> param = new HashMap<>();
        String url = "http://62.64.22.153:10002/biz_police_duty/api/createQwRwfzb";
        param.put("cjrdw", "410100000000");
        param.put("pid", changFeng.getPid());
        param.put("px", "1");
        param.put("rwzmc", changFeng.getRwzmc());
        param.put("rwzms", changFeng.getRwzms());
        String  paramString= JSON.toJSONString(param);
        String result = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("X-Bmob-Application-Id","2f0419a31f9casdfdsf431f6cd297fdd3e28fds4af")
                .header("X-Bmob-REST-API-Key","1e03efdas82178723afdsafsda4be0f305def6708cc6")
                .body(paramString)
                .execute().body();
        System.out.println("创建任务分组分组实例:" + result);
        //Map<String, Object> data = (Map<String, Object>) JSON.parse(s);
        return ResultUtil.setOK("success", result);
    }

    /**
     * 创建或修改任务实例
     * <p>
     * 创建或修改任务需填写所属任务组即rwzid,当传入rwid为空时为创建任务，修改时需传入原rwid
     * <p>
     * "cjr": "管理员",                                 创建人
     * "cjrdw": "410100000000",                         创建人所属单位  默认给诗句机构代码
     * "rwid":"",                                      创建任务时此字段要为空，修改是填写要修改任务的对应id
     * "jssj": "2020-05-20 07:58:45",                   结束时间
     * "kssj": "2020-05-30 07:58:45",                   开始时间
     * "rwmc": "测试创建任务C",                           任务名称
     * "rwms": "测试创建任务C"，                          任务明细
     * "rwzid": "8ea331e0e5df4d92a7a0e54aa3454659",     所属任务组id
     * "sgsj": "08:00:00",                              上岗时间  格式[HH:mm:ss]
     * "typeZB": [5,...],                               警员佩戴的装备
     * "userIds": ["106839","263716"],                  关联的警员String类型的数据传送
     * "xgsj": "08:00:00",                              下岗时间  格式[HH:mm:ss]
     * "ydrs": 1                                        应道人数
     *
     * @return
     */
    @PostMapping("createExample")
    public ResultBean createExample(Cfparticulars cfparticulars) {
        System.out.println(cfparticulars.getJssj());
        System.out.println(cfparticulars.getKssj());

        Map<String, Object> param = new HashMap<>();
        String url = "http://62.64.22.153:10002/biz_police_duty/api/saveOrUpdateDuty";
        param.put("cjr", cfparticulars.getCjr());
        param.put("rwid", cfparticulars.getRwid());
        param.put("jssj", cfparticulars.getJssj());
        param.put("kssj", cfparticulars.getKssj());
        param.put("rwmc", cfparticulars.getRwmc());
        param.put("rwms", cfparticulars.getRwms());
        param.put("rwzid", cfparticulars.getRwzid());
        param.put("sgsj", cfparticulars.getSgsj());
        param.put("typeZB", cfparticulars.getUserIds());
        param.put("userIds", cfparticulars.getUserIds());
        param.put("xgsj", cfparticulars.getXgsj());
        param.put("ydrs", cfparticulars.getYdrs());
        String paramString = JSON.toJSONString(param);
        System.out.println("参数：" + paramString);
        String result = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("X-Bmob-Application-Id", "2f0419a31f9casdfdsf431f6cd297fdd3e28fds4af")
                .header("X-Bmob-REST-API-Key", "1e03efdas82178723afdsafsda4be0f305def6708cc6")
                .body(paramString)
                .execute().body();
        System.out.println("创建或修改任务实例:" + result);
        //Map<String, Object> data = (Map<String, Object>) JSON.parse(s);
        return ResultUtil.setOK("success", result);
    }

}
