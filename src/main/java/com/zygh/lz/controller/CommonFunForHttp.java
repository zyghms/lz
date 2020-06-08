package com.zygh.lz.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.zygh.lz.dao.SectionMapper;
import com.zygh.lz.dao.StaffMapper;
import com.zygh.lz.dao.XareaMapper;
import com.zygh.lz.entity.Cfparticulars;
import com.zygh.lz.entity.ChangFeng;
import com.zygh.lz.entity.Section;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对接长峰科技第三方接口类
 */
@RestController
public class CommonFunForHttp {
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private XareaMapper xareaMapper;

    //获取是否签到签退信息
    @GetMapping("findRealTime")
    public ResultBean findRealTime(String staffnum) {
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        String result1 = HttpUtil.get("http://62.64.22.153:10002/biz_police_duty/api/findQwRwxxOnDutyByUserId/" + staffnum);
        return ResultUtil.setOK("success", result1);
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
        String paramString = JSON.toJSONString(param);
        String result = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("X-Bmob-Application-Id", "2f0419a31f9casdfdsf431f6cd297fdd3e28fds4af")
                .header("X-Bmob-REST-API-Key", "1e03efdas82178723afdsafsda4be0f305def6708cc6")
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
        String result = null;
        List<Section> selectBySublevel = sectionMapper.findSelectBySublevel(74);
        for (int i = 0; i < selectBySublevel.size(); i++) {
            Map<String, Object> param = new HashMap<>();
            String url = "http://62.64.22.153:10002/biz_police_duty/api/createQwRwfzb";
            param.put("cjrdw", "410100000000");
            param.put("pid", changFeng.getPid());
            param.put("px", "1");
            param.put("rwzmc", selectBySublevel.get(i).getSectionName());
            param.put("rwzms", changFeng.getRwzms());
            String paramString = JSON.toJSONString(param);
            result = HttpRequest.post(url)
                    .header("Content-Type", "application/json")
                    .header("X-Bmob-Application-Id", "2f0419a31f9casdfdsf431f6cd297fdd3e28fds4af")
                    .header("X-Bmob-REST-API-Key", "1e03efdas82178723afdsafsda4be0f305def6708cc6")
                    .body(paramString)
                    .execute().body();
            System.out.println("创建任务分组分组实例:" + result);
        }
        return ResultUtil.setOK("success", result);
    }

    /**
     * 创建或修改任务实例
     * <p>
     * 创建或修改任务需填写所属任务组即rwzid,当传入rwid为空时为创建任务，修改时需传入原rwid
     * <p>
     * "cjr": "管理员",                                  创建人
     * "cjrdw": "410100000000",                         创建人所属单位  默认给诗句机构代码
     * "rwid":"",                                       创建任务时此字段要为空，修改是填写要修改任务的对应id
     * "jssj": "2020-05-20 07:58:45",                   结束时间 UTC格式
     * "kssj": "2020-05-30 07:58:45",                   开始时间 UTC格式
     * "rwmc": "测试创建任务C",                           任务名称
     * "rwms": "测试创建任务C"，                          任务明细
     * "rwzid": "8ea331e0e5df4d92a7a0e54aa3454659",     所属任务组id
     * "sgsj": "08:00:00",                              上岗时间  格式[HH:mm:ss]
     * "typeZB": [5,...],                               此任务携带装备类型 传如装备类型对应代码 [警务通-1；车-2；摩托车-3；4G执法记录仪-4；PDT-5；单兵-6；布控球-7；无人机-8；直升机-9；船载-10；出租车-11]
     * "userIds": ["106839","263716"],                  关联的警员String类型的数据传送
     * "xgsj": "08:00:00",                              下岗时间  格式[HH:mm:ss]
     * "ydrs": 1                                        应道人数
     *
     * @return
     */
    @PostMapping("createExample")
    public ResultBean createExample(@RequestBody Cfparticulars cfparticulars) throws ParseException {

        System.out.println(cfparticulars);
        JSONObject json = new JSONObject();
        String url = "http://62.64.22.153:10002/biz_police_duty/api/saveOrUpdateDuty";
        json.put("cjr", cfparticulars.getCjr());
        json.put("cjrdw", cfparticulars.getCjrdw());
        json.put("rwid", cfparticulars.getRwid());
        json.put("jssj", cfparticulars.getJssj());
        json.put("kssj", cfparticulars.getKssj());
        json.put("rwmc", cfparticulars.getRwmc());
        json.put("rwms", cfparticulars.getRwms());
        json.put("rwzid", cfparticulars.getRwzid());
        json.put("sgsj", cfparticulars.getSgsj());
        json.put("typeZB", cfparticulars.getTypeZB());
        json.put("userIds", cfparticulars.getUserIds());
        json.put("xgsj", cfparticulars.getXgsj());
        json.put("ydrs", cfparticulars.getYdrs());
        System.out.println("参数：>>>>>>>>>>>>>>>>" + json);
        String result = HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .header("X-Bmob-Application-Id", "2f0419a31f9casdfdsf431f6cd297fdd3e28fds4af")
                .header("X-Bmob-REST-API-Key", "1e03efdas82178723afdsafsda4be0f305def6708cc6")
                .body(json)
                .execute().body();
        System.out.println("创建或修改任务实例:" + result);
        return ResultUtil.setOK("success", result);
    }

    public void jiekou(){
        //查出大队的任务组名称
        List<Section> levelMenu = xareaMapper.findLevelMenu(25);
        for (int i = 0; i < levelMenu.size(); i++) {
            JSONObject json = new JSONObject();
            String url = "http://62.64.22.153:10002/biz_police_duty/api/saveOrUpdateDuty";
            json.put("cjr", "管理员");
            json.put("cjrdw", "410100000000");
            json.put("rwid", "");
            json.put("jssj", "2020-06-06 17:01:00");
            json.put("kssj", "2020-06-06 17:01:00");
            json.put("rwmc", levelMenu.get(i).getSectionName());
            json.put("rwms", "详情");
            json.put("rwzid", "");
            json.put("sgsj", "08:00:00");
            json.put("typeZB", "");
            json.put("userIds", "");
            json.put("xgsj", "");
            json.put("ydrs", "");
            System.out.println("第"+i+"参数：>>>>>>>>>>>>>>>>" + json);
            String result = HttpRequest.post(url)
                    .header("Content-Type", "application/json")
                    .header("X-Bmob-Application-Id", "2f0419a31f9casdfdsf431f6cd297fdd3e28fds4af")
                    .header("X-Bmob-REST-API-Key", "1e03efdas82178723afdsafsda4be0f305def6708cc6")
                    .body(json)
                    .execute().body();
            System.out.println("第"+i+"次创建或修改任务实例:" + result);
        }
    }


}
