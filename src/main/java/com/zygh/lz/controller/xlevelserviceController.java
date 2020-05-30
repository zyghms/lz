package com.zygh.lz.controller;

import com.zygh.lz.service.XlevelserviceService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class xlevelserviceController {
    @Autowired
    private XlevelserviceService xlevelserviceService;

    //按大队统计等级勤务所有应到人数
    @GetMapping("selectorderlyAllqw")
    public ResultBean selectorderlyAll(String sectionName){
        return xlevelserviceService.selectorderlyAll(sectionName);
    }
    //按部门
    @GetMapping("selectorderlydjyd")
    public ResultBean selectorderlydjyd(String sectionName){
        return xlevelserviceService.selectorderlydjyd(sectionName);
    }

    //按等级按大队查询等级勤务
    @GetMapping("selectxleveBydj")
    public ResultBean selectxleveBydj(Integer hierarchy,String sectionName){
        return xlevelserviceService.selectxleveBydj(hierarchy,sectionName);
    }

    //按等级按大队查询等级勤务
    @GetMapping("selectXlevedJ")
    public ResultBean selectXlevedJ(Integer hierarchy){
        return xlevelserviceService.selectXlevedJ(hierarchy);
    }



}
