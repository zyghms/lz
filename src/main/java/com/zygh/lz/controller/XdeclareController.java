package com.zygh.lz.controller;

import com.zygh.lz.entity.Xdeclare;
import com.zygh.lz.service.XdeclareService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XdeclareController {
    @Autowired
    private XdeclareService xdelclareService;
    //报备新增
    @PostMapping("insertXdeclare")
    public ResultBean insertXdeclare(Xdeclare xdeclare){
        return xdelclareService.insertXdeclare(xdeclare);
    }

    //报备接受默认人
    @GetMapping("selectDefaultMan")
    public ResultBean selectDefaultMan(Integer staffid){
        return xdelclareService.selectDefaultMan(staffid);
    }

}
