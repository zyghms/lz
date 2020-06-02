package com.zygh.lz.controller;

import com.zygh.lz.config.WebSocketServer;
import com.zygh.lz.util.HttpClientUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 对接长峰科技第三方接口类
 */
@RestController
public class CommonFunForHttp {

    //获取是否签到签退信息
    @GetMapping("findRealTime")
    public String findRealTime(String staffnum) throws IOException {
        Boolean flas = true;
        Map<String, String> param = new HashMap<>();
        //String url = "http://62.64.22.153:10002/api/findRwxxTimeOut";
        String url = "http://127.0.0.1:8090/selectStaffByid";
        param.put("id", staffnum);
        //返回数据
        String s = HttpClientUtil.doGet(url, param);

        WebSocketServer.sendInfo(staffnum, null);
        System.out.println("===========================是否签到签退返回信息"+s);
        return s;
    }

}
