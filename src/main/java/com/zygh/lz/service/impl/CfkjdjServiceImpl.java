package com.zygh.lz.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zygh.lz.config.WebSocketServer;
import com.zygh.lz.dao.CfkjdjMapper;
import com.zygh.lz.entity.Cfkjdj;
import com.zygh.lz.service.CfkjdjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CfkjdjServiceImpl implements CfkjdjService {
    @Autowired
    private CfkjdjMapper cfkjdjMapper;

    @Override
    public void notSignIn() throws IOException {

        List<String> wsxList = cfkjdjMapper.findStaffBywsx();
        long startTime=System.nanoTime();   //获取开始时间


        for (String wsx : wsxList) {

            System.out.println("《《《《《《《长峰科技签到接口");
            // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
            String result1 = null;
            try {
                result1 = HttpUtil.get("http://62.64.22.153:10002/biz_police_duty/api/findQwRwxxOnDutyByUserId/" + wsx);
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("请求超时");
                result1 = null;
            }

            if (result1 == null) {
                System.out.println("》》》》》》》该警员没有暂时没有签到！！！！！");
                continue;
            }

            //WebSocketServer.sendInfo(result1,"1");
            System.out.println("result1:<<<<<<<<" + result1);
            //获取签到人的信息
            //Map<String, Object> qdMap = (Map<String, Object>) JSON.parse(result1);
            JSONArray qdMap = JSONArray.parseArray(result1);
            String rwid = qdMap.getJSONObject(0).get("zxzt").toString();
            if (rwid.equals("1")){
                WebSocketServer.sendInfo(result1,wsx);
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
            }


        }

        long endTime=System.nanoTime(); //获取结束时间

        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");

    }

    @Override
    public void notSignBack() throws IOException {

        List<String> wqtList = cfkjdjMapper.findCfkjdjbywqt();

        for (String wqt : wqtList) {
            System.out.println("《《《《《《《长峰科技签到接口");
            // 最简单的HTTP请求，可以自动通过header等信息判断编码，不区分HTTP和HTTPS
            String result1 = null;
            try {
                result1 = HttpUtil.get("http://62.64.22.153:10002/biz_police_duty/api/findQwRwxxOnDutyByUserId/" + wqt);
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("请求超时");
                result1 = null;
            }

            if (result1 == null) {
                System.out.println("》》》》》》》该警员没有暂时没有签到！！！！！");
                continue;
            }
            //WebSocketServer.sendInfo(result1,"1");
            System.out.println("result1:<<<<<<<<" + result1);
            //获取签到人的信息
            //Map<String, Object> qdMap = (Map<String, Object>) JSON.parse(result1);
            JSONArray qdMap = JSONArray.parseArray(result1);
            String rwid = qdMap.getJSONObject(0).get("zxzt").toString();
            if (rwid.equals("0")){
                WebSocketServer.sendInfo(result1,wqt);
                Cfkjdj byUserid = cfkjdjMapper.findByUserid(wqt);

                Cfkjdj cfkjdj = new Cfkjdj();
                cfkjdj.setId(byUserid.getId());
                cfkjdj.setZxzt("0");

                cfkjdjMapper.updateByPrimaryKeySelective(cfkjdj);
            }

        }



    }
}
