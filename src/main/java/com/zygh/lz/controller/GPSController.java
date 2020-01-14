package com.zygh.lz.controller;

import com.zygh.lz.admin.Gps;
import com.zygh.lz.service.GpsService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GPSController {
    @Autowired
    private GpsService gpsService;

    //实时上传gps
    @PostMapping("addGps")
    public ResultBean addGps(Gps gps){
        return gpsService.addGps(gps);
    }
}
