package com.zygh.lz.service;

import com.zygh.lz.entity.Location;
import com.zygh.lz.vo.ResultBean;

import java.util.List;

public interface LocationService {
    ResultBean addLocation(Location location);
    ResultBean delLocation(Integer id);
    ResultBean selectLocation(Integer staffId);
    //实时获取警员坐标
    ResultBean selectLocationByNowday();
}
