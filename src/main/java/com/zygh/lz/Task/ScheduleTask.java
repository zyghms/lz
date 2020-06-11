package com.zygh.lz.Task;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zygh.lz.controller.DepartmentOrPersonController;
import com.zygh.lz.dao.CallDataMapper;
import com.zygh.lz.dao.SectionMapper;
import com.zygh.lz.dao.StaffMapper;
import com.zygh.lz.entity.CallData;
import com.zygh.lz.entity.Section;
import com.zygh.lz.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Description:定时任务
 * User:luanhuajuan
 * Date:2020-06-09 23:00
 */
//@EnableAsync        // 2.开启多线程 由于业务量不大暂时不需要开启多线程
@Configuration
@EnableScheduling
public class ScheduleTask {

    @Autowired(required = false)
     private SectionMapper sectionMapper;

    @Autowired(required = false)
    private StaffMapper staffMapper;

    @Autowired(required = false)
    private CallDataMapper callDataMapper;


     // @Scheduled(cron = "*/5 * * * * ?") //每隔5秒中执行一次 用于测试
    @Scheduled(cron = "0 0 3 * * ?")  //3.每天早上5点更新一次 正式
    //或直接指定时间间隔，例如：5秒 不需要
    //@Scheduled(fixedRate=5000)
    private void configureDepartmentTasks() {
    String  params="{\n" +
            "    \"thirdId\": \"xindajiean\",\n" +
            "    \"lastTime\": \"0\",\n" +
            "    \"pageNo\": 1,\n" +
            "    \"pageSize\": 10\n" +
            "    \n" +
            "}";
     DepartmentOrPersonController.getDepartmentinfosyn(params);
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());

    }

     //@Scheduled(cron = "*/5 * * * * ?") //每隔5秒中执行一次 用于测试
     @Scheduled(cron = "0 0 4 * * ?")  //3.每天早上6点更新一次
    //或直接指定时间间隔，例如：5秒 不需要
    //@Scheduled(fixedRate=5000)
    private void configurePersonTasks() {
     String params ="{\n" +
             "    \"thirdId\": \"xindajiean\",\n" +
             "    \"lastTime\": \"0\",\n" +
             "    \"pageNo\": 1,\n" +
             "    \"pageSize\": 10,\n" +
             "    \"code\": \"\",\n" +
             "    \"cardNo\": \"\"\n" +
             "}";
     DepartmentOrPersonController.getPersoninfosyn(params);
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());

    }
   // @Scheduled(cron = "*/60 * * * * ?") //每隔5秒中执行一次 用于测试
    @Scheduled(cron = "0 0 5 * * ?")  //3.每天早上点更新数据库表
    //或直接指定时间间隔，例如：5秒 不需要
    //@Scheduled(fixedRate=5000)
    private void updateUserInfo() {
            String data1=null;
            String data2=null;
            List<Staff> staffList=staffMapper.selectAllSection();    //查询人员信息
            //查询时间是今天 更新两条数据
             String names="人员信息";
            List<CallData> callDataList=callDataMapper.selectInfo(names);
            if("人员信息".equals(callDataList.get(0).getName())){
               data1=callDataList.get(0).getData();
            }
            System.out.println("111"+data1);
             JSONArray json1 = JSONArray.parseArray(data1);
            Staff staff = new Staff();
           String code=null;
        for(int i=0;i<json1.size();i++) {
            JSONObject jsonObject = json1.getJSONObject(i);
            System.out.println("数据" + jsonObject.get("name").toString());
            if (jsonObject.get("name") != null) {
                if (!jsonObject.get("name").toString().isEmpty()) {
                    String name = jsonObject.get("name").toString();
                    staff.setStaffName(name);
                }
            }
            if (jsonObject.get("sex") != null) {
                if (jsonObject.get("sex").toString() != null) {
                    String sex = jsonObject.get("sex").toString();
                    staff.setStaffSex(sex);
                }
            }
            if(jsonObject.get("code") != null){
                if (!jsonObject.get("code").toString().isEmpty()) {
                    code = jsonObject.get("code").toString();
                    staff.setStaffNum(code);
                    System.out.println(code);
                }
            }
            if(jsonObject.get("mobile") != null) {
                if (!jsonObject.get("mobile").toString().isEmpty()) {
                    String mobile = jsonObject.get("mobile").toString();
                    staff.setCellphonenumber(mobile);
                }
            }
            if(jsonObject.get("position") != null) {
                if (!jsonObject.get("position").toString().isEmpty()) {
                    String position = jsonObject.get("position").toString();
                    staff.setStaffPhoto(position);
                }
            }
            if(jsonObject.get("police") != null) {
                if (!jsonObject.get("police").toString().isEmpty()) {
                    String police = jsonObject.get("police").toString();
                    staff.setStafftype(police);
                }
            }
            if(jsonObject.get("dep_id") != null) {
                if (!jsonObject.get("dep_id").toString().isEmpty()) {
                    String dep_id = jsonObject.get("dep_id").toString();
                    staff.setSysSectionId(Integer.valueOf(dep_id));
                }
            }
            int count=0;
                 for (int j=0; j<staffList.size();j++){
                     System.out.println("staffnum="+staffList.get(j).getStaffNum());
                     System.out.println("code"+code);
                     count++;
                     if(staffList.get(j).getStaffNum()!=null && code!=null){
                         if(staffList.get(j).getStaffNum().equals(code)){
                            int result= staffMapper.updateInfo(staff);

                             System.out.println("更新人员信息返回结果"+result);
                             if(count == j){
                                 return ;
                             }
                         }
                     }


                  }
             }



    }
   // @Scheduled(cron = "*/5 * * * * ?") //每隔5秒中执行一次 用于测试
    @Scheduled(cron = "0 0 6 * * ?")  //3.每天早上点更新数据库表
    //或直接指定时间间隔，例如：5秒 不需要
    //@Scheduled(fixedRate=5000)
    private void updateDepartmentInfo() {
        System.out.println("qqqqqq");
        String data1=null;
        String data2=null;
        Section section =new Section();
        List <Section> sectionList=sectionMapper.selectAllSection();   //查询部门全部信息
        String names="部门信息";
        List<CallData> callDataList=callDataMapper.selectInfo(names);
        System.out.println(callDataList.get(0).getName());
        if("部门信息".equals(callDataList.get(0).getName())){
            data2=callDataList.get(0).getData();
        }
        System.out.println("测试2"+data2);
        JSONArray json2 = JSONArray.parseArray(data2);
        System.out.println("测试2"+json2);
        String name=null;
        for(int i=0;i<json2.size();i++){
            JSONObject jsonObject = json2.getJSONObject(i);
            int id=0;
            if(id!=0 ){
                id = Integer.parseInt(jsonObject.get("id").toString());
//                    section.setSysSectionId(id);
            }
            if( jsonObject.get("name")!=null){
                if( !jsonObject.get("name").toString().isEmpty()){
                    name= jsonObject.get("name").toString();
                        section.setSectionName(name);
                }

            }
            if(jsonObject.get("phone")!=null){
                if(!jsonObject.get("phone").toString().isEmpty()){
                    String phone= jsonObject.get("phone").toString();
                    section.setSectionTel(phone);
                }
            }

            if(jsonObject.get("contact")!=null){
                if(!jsonObject.get("contact").toString().isEmpty()) {
                    String contact = jsonObject.get("contact").toString();
                    section.setSectionPerson(contact);
                }
            }

            if(jsonObject.get("parent_id")!=null) {
                if (!jsonObject.get("parent_id").toString().isEmpty()) {
                    String parent_id = jsonObject.get("parent_id").toString();
                    section.setSectionPid(Integer.valueOf(parent_id));

                }
            }
            System.out.println("数据库"+sectionList.get(i).getSectionName());
            System.out.println("名称"+name);
            int count=0;
            for (int k=0; k<sectionList.size();k++){
                count++;
                if(sectionList.get(i).getSectionName().equals(name)){
                    int result=sectionMapper.updateInfo(section);
                    System.out.println("更新部门信息获取结果"+result);
                    if(count==k){
                        return;
                    }
                }

            }
        }
    }

}
