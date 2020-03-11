package com.zygh.lz.controller;

import com.zygh.lz.admin.Gps;
import com.zygh.lz.admin.Test;
import com.zygh.lz.admin.Xarea;
import com.zygh.lz.admin.Xlevelservice;
import com.zygh.lz.mapper.TestMapper;
import com.zygh.lz.mapper.XareaMapper;
import com.zygh.lz.mapper.XlevelserviceMapper;
import com.zygh.lz.service.GpsService;
import com.zygh.lz.util.GPSTransformMars;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GPSController {
    @Autowired
    private GpsService gpsService;
    @Autowired
    private XlevelserviceMapper xlevelserviceMapper;
    @Autowired
    private XareaMapper xareaMapper;
    @Autowired
    private TestMapper testMapper;

    //实时上传gps
    @PostMapping("addGps")
    public ResultBean addGps(Gps gps) {
        return gpsService.addGps(gps);
    }

    @GetMapping("gps")
    public void gps() {
        List<Test> tests = testMapper.selectTestAll();
        /*for (int i = 0; i < tests.size(); i++) {
            System.out.println("=====" + tests.get(i).getGps());
            if (tests.get(i).getGps() != null) {
                Test xarea = new Test();
                String s = GPSTransformMars.GCj2TOWGS(tests.get(i).getGps());

                xarea.setId(tests.get(i).getId());
                xarea.setGps(s);
                testMapper.updateByPrimaryKeySelective(xarea);
            }
        }*/

        List<Xarea> xareas = xareaMapper.selectXareaAll();
        for (int k = 0; k < xareas.size(); k++) {
            for (int i = 0; i < tests.size(); i++) {
                String id = xareas.get(k).getId().toString();
                String po = tests.get(i).getPo();
                if (id.equals(po)) {
                    Xarea xarea = new Xarea();
                    xarea.setId(xareas.get(k).getId());
                    xarea.setGps(tests.get(i).getGps());
                    xareaMapper.updateByPrimaryKeySelective(xarea);
                    System.out.println("=========" + xareas.get(k).getId());
                }
            }

        }

        //System.out.println(tests.size());

    }

}
