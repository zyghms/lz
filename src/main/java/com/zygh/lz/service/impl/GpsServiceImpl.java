package com.zygh.lz.service.impl;

import com.zygh.lz.entity.Gps;
import com.zygh.lz.entity.Location;
import com.zygh.lz.dao.GpsMapper;
import com.zygh.lz.dao.LocationMapper;
import com.zygh.lz.service.GpsService;
import com.zygh.lz.util.GCJ2WGSUtils;
import com.zygh.lz.util.GPSTransformMars;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GpsServiceImpl implements GpsService {
    @Autowired
    private GpsMapper gpsMapper;
    @Autowired
    private LocationMapper locationMapper;

    /**
     * 新增GPS
     * @param gps
     * @return
     */
    @Override
    public ResultBean addGps(Gps gps) {
        Location location=new Location();
        location.setSysStaffId(gps.getSysStaffId());
        location.setTime(new Date());
        GPSTransformMars gpsTransformMars = new GPSTransformMars();
        double x= Double.parseDouble(gps.getGpsX());
        double y= Double.parseDouble(gps.getGpsY());
        //double[] m= GCJ2WGSUtils.gcj02towgs84(x,y);
        gps.setGpsX(String.valueOf(x));
        gps.setGpsY(String.valueOf(y));
        location.setGpsX(String.valueOf(x));
        location.setGpsY(String.valueOf(y));
        locationMapper.updateByStaffId(location);
        return ResultUtil.execOp(gpsMapper.insertSelective(gps),"新增");
    }
}
