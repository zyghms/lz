package com.zygh.lz.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zygh.lz.dao.CfkjdjMapper;
<<<<<<< HEAD
import com.zygh.lz.dao.CfrwinfoMapper;
=======
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
import com.zygh.lz.dao.SectionMapper;
import com.zygh.lz.entity.*;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    private CfkjdjMapper cfkjdjMapper;
<<<<<<< HEAD
    @Autowired
    private CfrwinfoMapper cfrwinfoMapper;
=======
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4

    /**
     * 获取是否签到签退信息
     *
     * @param staffnum
     * @return
     */
    @GetMapping("findRealTime")
    public ResultBean findRealTime(String staffnum) throws IOException {
        Staff staff=new Staff();
        staff.setStaffNum(staffnum);
        System.out.println("《《《《《《《长峰科技签到接口");
        // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
        String result1 = HttpUtil.get("http://62.64.22.153:10002/biz_police_duty/api/findQwRwxxOnDutyByUserId/" + staffnum);

        if (result1 == null) {
            System.out.println("》》》》》》》该警员没有暂时没有签到！！！！！");
            return ResultUtil.setOK("success", null);
        }
        //WebSocketServer.sendInfo(result1,"1");
        System.out.println("result1:<<<<<<<<" + result1);
        //获取签到人的信息
        //Map<String, Object> qdMap = (Map<String, Object>) JSON.parse(result1);
        JSONArray qdMap = JSONArray.parseArray(result1);
        String rwid = null;
        Cfkjdj cfkjdj = new Cfkjdj();
        for (int i = 0; i < qdMap.size(); i++) {     //遍历json数组内容
            com.alibaba.fastjson.JSONObject object = qdMap.getJSONObject(i);
            System.out.println(object.getString("qdid"));
            rwid = (String) object.get("rwid");
            System.out.println("《《《《《《《《《=======" + rwid);
            cfkjdj.setQdid((String) object.get("qdid"));
            cfkjdj.setQdlat(String.valueOf(object.get("qdlat")));
            cfkjdj.setQdlng(String.valueOf(object.get("qdlng")));
            cfkjdj.setQdsj((String) object.get("qdsj"));
            cfkjdj.setUserid((String) object.get("userid"));
            cfkjdj.setUsername((String) object.get("username"));
            cfkjdj.setZblist(String.valueOf(object.get("zblist")));
            cfkjdj.setZhxgsj((String) object.get("zhxgsj"));
            cfkjdj.setZw((String) object.get("zw"));
            cfkjdj.setZzjgdm((String) object.get("zzjgdm"));
            cfkjdj.setZxzt(String.valueOf(object.get("zxzt")));
        }

        System.out.println("qdmap" + qdMap);

        //查询根据任务id查询任务
        System.out.println("任务id" + rwid);
        String result2 = HttpUtil.get("http://62.64.22.153:10002/biz_police_duty/api/findRwxxByRwid?rwid=" + rwid);
        System.out.println(">>>>>>result2" + result2);
        Map<String, Object> map = (Map<String, Object>) JSON.parse(result2);
        Map<String, Object> objectrw = (Map<String, Object>) JSON.parse((map.get("data").toString()));
        String jssj = objectrw.get("jssj").toString();
        cfkjdj.setJssj(jssj);
        String kssj = objectrw.get("kssj").toString();
        cfkjdj.setKssj(kssj);
        System.out.println("开始时间：" + kssj);
        System.out.println("结束时间：" + jssj);
        cfkjdj.setRwid((String) objectrw.get("rwid"));
        cfkjdj.setRwmc((String) objectrw.get("rwmc"));
        cfkjdj.setRwms((String) objectrw.get("rwms"));
        cfkjdj.setRwzid((String) objectrw.get("rwzid"));
        cfkjdj.setRwzmc((String) objectrw.get("rwzmc"));
        cfkjdj.setRwzms((String) objectrw.get("rwzms"));


        //存储到数据库任务岗位，警号，任命签到时间，签到经纬度
        int i = cfkjdjMapper.insertSelective(cfkjdj);
        if (i > 0) {
            System.out.println("《《《《《签到信息存储成功！！！");
        }
        return ResultUtil.setOK("success", result1);
    }

   /* public static void main(String[] args) {
        String a = "{\"msg\":\"success\",\"code\":0,\"data\":{\"jssj\":\"2020-06-08 11:30:45\",\"kssj\":\"2020-06-08 11:30:54\",\"rwid\":\"b08a5755f9154b96b5fd5e25d36d4cc5\",\"rwmc\":\"固定岗-金水路与文化路测1\",\"rwms\":\"测试数据68#[\\\"车\\\",\\\"警务通\\\",\\\"摩托车\\\"]\",\"rwzid\":\"af02e4ed2ece44dba9be8703ce28cffb\",\"rwzmc\":\"高架大队\",\"rwzms\":\"测试数据68\"}}";
        //com.alibaba.fastjson.JSONObject
       *//* Object parse = com.alibaba.fastjson.JSONObject.parse(a);

        JSONArray  jsonArray  = JSONArray.parseArray(a);
        for (int i = 0; i < jsonArray.size(); i++) {     //遍历json数组内容
            com.alibaba.fastjson.JSONObject object = jsonArray.getJSONObject(i);
            System.out.println(object.getString("qdid"));
        }*//*
        Map<String, Object> map = (Map<String, Object>) JSON.parse(a);
        Map<String, Object> policeMap = (Map<String, Object>) JSON.parse((map.get("data").toString()));
        System.out.println(policeMap);

    }*/

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
<<<<<<< HEAD
        //List<Section> selectBySublevel = sectionMapper.findSelectBySublevel(74);
       // for (int i = 0; i < selectBySublevel.size(); i++) {
=======
        List<Section> selectBySublevel = sectionMapper.findSelectBySublevel(74);
        for (int i = 0; i < selectBySublevel.size(); i++) {
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
            Map<String, Object> param = new HashMap<>();
            String url = "http://62.64.22.153:10002/biz_police_duty/api/createQwRwfzb";
            param.put("cjrdw", "410100000000");
            param.put("pid", changFeng.getPid());
            param.put("px", "1");
<<<<<<< HEAD
            //param.put("rwzmc", selectBySublevel.get(i).getSectionName());
            param.put("rwzmc", changFeng.getRwzmc());
=======
            param.put("rwzmc", selectBySublevel.get(i).getSectionName());
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
            param.put("rwzms", changFeng.getRwzms());
            String paramString = JSON.toJSONString(param);
            result = HttpRequest.post(url)
                    .header("Content-Type", "application/json")
                    .header("X-Bmob-Application-Id", "2f0419a31f9casdfdsf431f6cd297fdd3e28fds4af")
                    .header("X-Bmob-REST-API-Key", "1e03efdas82178723afdsafsda4be0f305def6708cc6")
                    .body(paramString)
                    .execute().body();
            System.out.println("创建任务分组分组实例:" + result);
<<<<<<< HEAD
      //  }
=======
        }
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
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
     * "typeZB": [5,...],                               警员佩戴的装备
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
<<<<<<< HEAD
        //新建任务存储数据库
        Cfrwinfo cfrwinfo = new Cfrwinfo();
        cfrwinfo.setJssj(cfparticulars.getJssj());
        cfrwinfo.setKssj(cfparticulars.getKssj());
        cfrwinfo.setRwmc(cfparticulars.getRwmc());
        cfrwinfo.setRwms(cfparticulars.getRwms());
        cfrwinfo.setRwzid(cfparticulars.getRwzid());
        cfrwinfoMapper.insertSelective(cfrwinfo);
        System.out.println(">>>>>添加任务成功！！");
=======
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
        return ResultUtil.setOK("success", result);
    }

}
