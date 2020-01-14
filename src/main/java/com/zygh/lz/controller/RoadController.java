package com.zygh.lz.controller;

import com.zygh.lz.admin.Road;
import com.zygh.lz.service.roadService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoadController {
    @Autowired
    private roadService roadService;

    //新增
    @PostMapping("addRoad")
    public ResultBean addRoad(Road road) {
        return roadService.addRoad(road);
    }


    //修改
    @GetMapping("updateRoad")
    public ResultBean updateRoad(Road road) {
        return roadService.updateRoad(road);
    }


    //删除
    @GetMapping("delRoadById")
    public ResultBean delRoadById(Integer id) {
        return roadService.delRoadById(id);
    }

    //根据道路类型、区域模糊查询
    @GetMapping("selectRoadByCondition")
    public ResultBean selectRoadByCondition(String keyword,String roadType, String urbanName) {
        return roadService.selectRoadByCondition(keyword,roadType, urbanName);
    }

    //查询所有道路列表
    @GetMapping("selectAllRoad")
    public ResultBean selectAllRoad(){
        return roadService.selectAllRoad();
    }

}
