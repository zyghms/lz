package com.zygh.lz.util;

import com.zygh.lz.entity.Staff;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author feri
 *@Date Created in 2018/11/29 17:23
 */
public class StringUtil {
    public static boolean checkEmpty(String... values){
        boolean r=false;
        for(String v:values){
            if(!(v!=null && v.length()>0)){
                r=true;
            }
        }
        return r;
    }
    public static boolean checkNoEmpty(String... values){
        boolean r=true;
        for(String v:values){
            if(!(v!=null && v.length()>0)){
                r=false;
            }
        }
        return r;
    }


    public static String findRealTime(String staffnum) throws IOException {
        System.out.println("参数接受："+staffnum);
        Staff staff=new Staff();
        staff.setStaffNum(staffnum);
        Boolean flas = true;
        Map<String, String> param = new HashMap<>();
        //String url = "http://62.64.22.153:10002/api/findRwxxTimeOut";
        String url = "http://127.0.0.1:8090/selectStaffByid";
        param.put("id", staffnum);
        //返回数据
        String s = HttpClientUtil.doGet(url, param);
        System.out.println("===========================是否签到签退返回信息"+s);
        return s;
    }
}
