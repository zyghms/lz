package com.zygh.lz.controller;

import com.zygh.lz.service.xlevelserviceService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class xlevelserviceController {
    @Autowired
    private xlevelserviceService xlevelserviceService;

    //等级勤务所有应到人数
    @GetMapping("selectorderlyAllqw")
    public ResultBean selectorderlyAll(String sectionName){
        return xlevelserviceService.selectorderlyAll(sectionName);
    }

    @GetMapping("selectorderlydjyd")
    public ResultBean selectorderlydjyd(String sectionName){
        return xlevelserviceService.selectorderlydjyd(sectionName);
    }


}
