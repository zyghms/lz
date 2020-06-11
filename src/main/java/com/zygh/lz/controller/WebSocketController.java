package com.zygh.lz.controller;

import com.alibaba.fastjson.JSON;
import com.zygh.lz.config.WebSocketServer;
import com.zygh.lz.entity.Staff;
import com.zygh.lz.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WebSocketController {


    @RequestMapping("clj")
    public void clj(String staffnum) throws Exception {


        while (true) {
            Staff staff = new Staff();
            String message = StringUtil.findRealTime(staffnum); // 第三方接口返回数据
            Map<String, Object> objectrw = null;
            try {
                Map<String, Object> map = (Map<String, Object>) JSON.parse(message);
                objectrw = (Map<String, Object>) JSON.parse((map.get("data").toString()));
            } catch (Exception e) {
                objectrw=null;
            }
            if (objectrw != null) {
                WebSocketServer.sendInfo(message, "1"); // 推送
            }
            Thread.sleep(5000);

        }

    }
}
