package com.zygh.lz.controller;

import com.zygh.lz.entity.Sptype;
import com.zygh.lz.service.SptypeService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SptypeController {

    @Autowired
    private SptypeService sptypeService;

    //删除特殊勤务
    @GetMapping("delSptype")
    public ResultBean delSptype(Integer id ){
        return sptypeService.delSptype(id);
    }

    //添加勤务
    @GetMapping("addSptype")
    public ResultBean addSptype(Sptype sptype ){
        return sptypeService.addSptype(sptype);
    }

    //修改特殊勤务
    @GetMapping("updateSptype")
    public ResultBean updateSptype(Sptype sptype){
        return sptypeService.updateSptype(sptype);
    }


}
