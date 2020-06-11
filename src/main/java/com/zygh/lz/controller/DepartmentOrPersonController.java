package com.zygh.lz.controller;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.hutool.http.HttpUtil;
import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSON;
import com.zygh.lz.dao.CallDataMapper;
import com.zygh.lz.dao.SectionMapper;
import com.zygh.lz.dao.StaffMapper;
import com.zygh.lz.entity.CallData;
import com.zygh.lz.entity.Section;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 对接单位信息同步接口 和人员结构信息接口
 */
@RestController
@Slf4j
public class DepartmentOrPersonController {
    private static String urls = "http://62.64.11.7:9010/";

    @Autowired
    private   static  CallDataMapper callDataMapper;


    @PostMapping("getDepartmentinfosyn")
    public static ResultBean getDepartmentinfosyn(@RequestBody String params) {
        try {
            CallData callData=new CallData();
            log.info("传入的参数信息" + params);
            JSONObject json = JSONObject.fromObject(params);
            log.info("插入的数据信息" + json);
            urls = urls + "pams/sso/basicinfosynchronizecontrol/departmentinfosyn.do";
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("thirdId", json.get("thirdId"));
            paramMap.put("lastTime", json.get("lastTime"));
            paramMap.put("pageNo", json.get("pageNo"));
            paramMap.put("pageSize", json.get("pageSize"));
            String result = HttpUtil.post(urls, paramMap);
            System.out.println("返回部门信息数据:" + result);
            JSONObject jsons = JSONObject.fromObject(result);
            if(jsons.get("list").toString()!=null){
                JSONArray jsonArray = JSONArray.fromObject(jsons.getString("list"));
                System.out.println(jsonArray);
                callData.setData(jsonArray.toString());
            }

            callData.setName("部门信息");
            callData.setResult(result);
            callData.setCjsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            callDataMapper.insert(callData);
            return ResultUtil.setOK("success", result);

//    JSONArray jsonArray = JSONArray.fromObject(jsons.getString("list"));
//    log.info("部门信息list接口："+jsonArray);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResultUtil.setError(500, "出现错误",null);


    }

    @PostMapping("getPersoninfosyn")
    public static ResultBean getPersoninfosyn(@RequestBody String params) {
        try {
            CallData callDats=new CallData();
            log.info("传入的参数信息" + params);
            JSONObject json = JSONObject.fromObject(params);
            log.info("插入的数据信息" + json);
            urls = urls + "pams/sso/basicinfosynchronizecontrol/personinfosyn.do";
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("thirdId", json.get("thirdId"));
            paramMap.put("lastTime", json.get("lastTime"));
            paramMap.put("pageNo", json.get("pageNo"));
            paramMap.put("pageSize", json.get("pageSize"));
            if(!json.get("code").toString().isEmpty()) {
                paramMap.put("code ", json.get("code"));
            }
            if(!json.get("cardNo").toString().isEmpty()) {
                paramMap.put("cardNo ", json.get("cardNo"));
            }
//            paramMap.put("code ", json.get("code"));
//            paramMap.put("cardNo ", json.get("cardNo"));
            String result = HttpUtil.post(urls, paramMap);
            System.out.println("返回人员信息数据:" + result);
            JSONObject jsonObject = JSONObject.fromObject(result);
            if(jsonObject.get("list").toString()!=null){
                JSONArray jsonArrays = JSONArray.fromObject(jsonObject.get("list"));
                callDats.setData(jsonArrays.toString());
            }
            callDats.setName("人员信息");
            callDats.setResult(result);
            callDats.setCjsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            callDataMapper.insert(callDats);
            return ResultUtil.setOK("success", result);

        }catch ( Exception e){
            e.printStackTrace();
        }
        return ResultUtil.setError(500, "出现错误",null);

    }


    public static void main(String[] args) {
        String result = "{\n" +
                "\t\"result\": {\n" +
                "\t\t\"message\": \"提示信息\",\n" +
                "\t\t\"flag\": \"操作结果\"\n" +
                "\t},\n" +
                "\t\"count\": \"查询总数\",\n" +
                "\t\"time\": \"服务器当前时间戳\",\n" +
                "\t\"list\": [{\n" +
                "\t\t\"id\": 74,\n" +
                "\t\t\"callsign\": \"交警支队\",\n" +
                "\t\t\"place\": \"郑州市三全路66号\",\n" +
                "\t\t\"subofficeId\": null,\n" +
                "\t\t\"number\": 0,\n" +
                "\t\t\"sectionId\": null,\n" +
                "\t\t\"ondutytime\": null,\n" +
                "\t\t\"location\": \"123456\",\n" +
                "\t\t\"hierarchy\": \"管理员\"\n" +
                "\t}]\n" +
                "\n" +
                "}";
       // String formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (jsonObject.get("count").toString() != null) {
            System.out.println("123" + "true");
        }
        if (!jsonObject.get("count").toString().isEmpty()) {
            System.out.println(!jsonObject.get("count").toString().isEmpty());
        }
        if (jsonObject.get("time").toString() != null) {
            System.out.println("456" + "true");
        }

        System.out.println("提示信息" + jsonObject);
        System.out.println("aaaa" + jsonObject.get("list").toString() != null);
//        System.out.println("aaaa"+jsonObject.get("list").toString().isEmpty());
        if (!jsonObject.get("list").toString().isEmpty()) {
            System.out.println(1);
        }
        System.out.println("aaaa" + jsonObject.get("list"));
        if (jsonObject.get("list").toString() != null) {
            JSONArray jsonArrays = JSONArray.fromObject(jsonObject.get("list"));
            System.out.println("123" + jsonArrays);
        }


    }

}
