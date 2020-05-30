package com.zygh.lz.controller;

import com.zygh.lz.entity.Duty;
import com.zygh.lz.service.DutyService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DutyController {
    @Autowired
    private DutyService dutyService;

    //责任明细
    @GetMapping("selectAllDuty")
    public ResultBean selectAllDuty(){
        return dutyService.selectAllDuty();
    }


    //模糊查询
    @GetMapping("seleteDimAsset")
    public ResultBean seleteDimAsset(Duty duty){
        return dutyService.seleteDimAsset(duty);
    }

    //删除
    @GetMapping("deleteSomeDuty")
    public ResultBean deleteSomeDuty(Integer id){
        return dutyService.delDuty(id);
    }

    //修改
    @GetMapping("updateDuty")
    public ResultBean updateDuty(Duty duty){
        return dutyService.updateDuty(duty);
    }
    //新增
    @PostMapping("insertDuty")
    public ResultBean insertDuty(Duty duty){
        return dutyService.addDuty(duty);
    }

}
