package com.zygh.lz.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zygh.lz.entity.Staff;
import org.omg.CORBA.StringHolder;

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


        public static JSONObject makeReturnJsonStr(String retCode, String retMsg){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("retCode",retCode);
            jsonObject.put("retMsg","retMsg");
            return jsonObject;
        }

/*
        public static void main(String[] args){
           *//* String js="[{\"qdid\":\"6c7feaae0ed84077b323e77e0160b5de\",\"qdlat\":34.829433,\"qdlng\":113.675316,\"qdsj\":\"2020-06-08 17:04:19\",\"rwid\":\"b08a5755f9154b96b5fd5e25d36d4cc5\",\"sfzdxx\":0,\"userid\":\"100728\",\"username\":\"翟玉斌\",\"zblist\":[],\"zhxgsj\":\"2020-06-08 17:04:19\",\"zw\":\"14\",\"zxzt\":1,\"zzjgdm\":\"410100171100\"}]\n" +
                    "\n";
            JSONObject jsonObject = JSONObject.parseObject(js);//字符串转json对象
            String data = jsonObject.getString("DS");//获取DS内容
            JSONArray jsonArray = JSONArray.fromObject(data);//并将DS内容取出转为json数组
            for (int i = 0; i < jsonArray.size(); i++) {     //遍历json数组内容
                JSONObject object = jsonArray.getJSONObject(i);
                System.out.println(object.getString("字段名1"));
            }*//*
        }*/

}
