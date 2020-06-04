package com.zygh.lz.service.impl;

import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.entity.GisLocation;
import com.zygh.lz.service.GisLocationService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.stereotype.Service;
import com.zygh.lz.dao.GisLocationMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GisLocationServiceImpl implements GisLocationService {

    @Resource
    private  GisLocationMapper gisLocationMapper;

    @Override
    public ResultBean selectGisLocation() {
        List<GisLocation> gisLocationList = gisLocationMapper.selectGisLocation();
        if(gisLocationList.size() > 0){
            return ResultUtil.setOK("success",gisLocationList);
        }
        return ResultUtil.setError(SystemCon.RERROR1,"error",null);

    }



}
