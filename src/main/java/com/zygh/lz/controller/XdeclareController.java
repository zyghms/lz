package com.zygh.lz.controller;

import com.zygh.lz.admin.Xdeclare;
import com.zygh.lz.service.xdeclareService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XdeclareController {
    @Autowired
    private xdeclareService xdelclareService;

    @PostMapping("insertXdeclare")
    public ResultBean insertXdeclare(Xdeclare xdeclare){
        return xdelclareService.insertXdeclare(xdeclare);
    }

}
