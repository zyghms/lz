package com.zygh.lz.controller;

import com.zygh.lz.admin.Location;
import com.zygh.lz.service.locationService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
    @Autowired
    private locationService locationService;

    @PostMapping("addLocation")
    public ResultBean addLocation(Location location){
        return locationService.addLocation(location);
    }

    @GetMapping("delLocation")
    public ResultBean delLocation(Integer id){
        return locationService.delLocation(id);
    }

    @GetMapping("selectLocation")
    public ResultBean selectLocation(Integer staffId){
        return locationService.selectLocation(staffId);
    }

    //实时获取警员坐标
    @GetMapping("selectLocationByNowday")
    public ResultBean selectLocationByNowday(){
        return locationService.selectLocationByNowday();
    }
}
