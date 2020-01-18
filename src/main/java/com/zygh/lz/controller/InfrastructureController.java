package com.zygh.lz.controller;

import com.zygh.lz.admin.Infrastructure;
import com.zygh.lz.service.InfrastructureService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InfrastructureController {
    @Autowired
    private InfrastructureService infrastructureService;

    //新增交通设施信息
    @PostMapping("addInfrastructure")
    public ResultBean addInfrastructure(Infrastructure infrastructure) {
        return infrastructureService.addInfrastructure(infrastructure);
    }

    //修改交通设施信息
    @GetMapping("modifuInfrastructure")
    public ResultBean updateInfrastructure(Infrastructure infrastructure) {
        return infrastructureService.updateInfrastructure(infrastructure);
    }

    //根据id删除交通设施信息
    @GetMapping("delInfrastructure")
    public ResultBean delInfrastructure(Integer id) {
        return infrastructureService.delInfrastructure(id);
    }

    //根据交通设施类型、道路名模糊查询
    @GetMapping("selectInfrastructureByInfo")
    public ResultBean selectInfrastructureByInfo(String type, String roadName) {
        return infrastructureService.selectInfrastructureByInfo(type, roadName);
    }
}
