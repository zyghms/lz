package com.zygh.lz.controller;

import com.zygh.lz.dao.TestMapper;
import com.zygh.lz.dao.XareaMapper;
import com.zygh.lz.dao.XlevelserviceMapper;
import com.zygh.lz.entity.Gps;
import com.zygh.lz.entity.Test;
import com.zygh.lz.entity.Xarea;
import com.zygh.lz.service.GpsService;
import com.zygh.lz.service.XrelationService;
import com.zygh.lz.util.GCJ2WGSUtils;
import com.zygh.lz.util.GPSTransformMars;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("gps84")
    public void gps() {
        List<Xarea> tests = xareaMapper.selectXareaAll();
        for (int i = 0; i < tests.size(); i++) {
            System.out.println("=====" + tests.get(i).getCentre());
            if (tests.get(i).getCentre() != null && !tests.get(i).getCentre().equals("")) {
                Xarea xarea = new Xarea();
                String s = GPSTransformMars.GCj2TOWGS(tests.get(i).getCentre());
                xarea.setId(tests.get(i).getId());
                xarea.setCentre(s);
                xareaMapper.updateByPrimaryKeySelective(xarea);
            }
        }

       /* List<Xarea> xareas = xareaMapper.selectXareaAll();
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

        }*/

        //System.out.println(tests.size());

    }
    @GetMapping("pgs")
    public String pgs(String gps){
        //String gps="113.68909888362623,34.763538722849184,113.68551653471172,34.763674117695814,113.6820210988152,34.76377744384686,113.68205193275367,34.7602108935337,113.69152414242456,34.7599760430121,113.6916512642789,34.76354434981144";
        String[] split = gps.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
        String loastr = "";
        String loc = "";
        for (int k = 0; k < split.length; k++) {
            if (k % 2 == 0) {
                double lat = Double.valueOf(split[k + 1]); //标记纬度
                double lng = Double.valueOf(split[k]);     //标记经度
                double[] doubles = GCJ2WGSUtils.gcj02towgs84(lng, lat);
                System.out.println();
                loastr = String.valueOf(doubles[0]) + "," + String.valueOf(doubles[1])+",";
                loc += loastr;
            }
        }

        System.out.println(loc.substring(0,loc.length()-1));
        return loc.substring(0,loc.length()-1);
    }

}
