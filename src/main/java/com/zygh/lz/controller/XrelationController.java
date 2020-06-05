package com.zygh.lz.controller;

import com.zygh.lz.entity.Xrelation;
import com.zygh.lz.service.XrelationService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XrelationController {
    @Autowired
    private XrelationService xrelationService;
    //新增关联
    @PostMapping("insertXrelation")
    public ResultBean insertXrelation(Xrelation xrelation){
        return xrelationService.insertXrelation(xrelation);
    }
    //修改
    @GetMapping("updateXrelation")
    public ResultBean updateXrelation(Xrelation xrelation){
        return xrelationService.updateXrelation(xrelation);
    }
    //删除
    @GetMapping("deleteXrelatonByid")
    public ResultBean  deleteXrelatonByid(Integer staffId,Integer xareaId){
        return xrelationService.deleteXrelatonByid(staffId,xareaId);
    }
}
