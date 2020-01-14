package com.zygh.lz.service.impl;

import com.zygh.lz.mapper.XlevelserviceMapper;
import com.zygh.lz.service.xlevelserviceService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class xlevelserviceServiceImpl implements xlevelserviceService {
    @Autowired
    private XlevelserviceMapper xlevelserviceMapper;

    /**
     * 查询全部等级勤务
     * @return
     */
    @Override
    public ResultBean selectorderlyAll() {
        List<HashMap> maps = xlevelserviceMapper.selectorderlyAll();
        return ResultUtil.setOK("success",maps);
    }

    /**
     * 等级勤务
     * @return
     */
    @Override
    public ResultBean selectorderlydjyd() {
        Map<String,Object> map=new HashMap<>();
        //一级应到
        List<HashMap> selectorderlyoneyd = xlevelserviceMapper.selectorderlyoneyd();
        for (int i=0;i<selectorderlyoneyd.size();i++){
            selectorderlyoneyd.get(i).put("SDnumber",0);
        }
        //二级应到
        List<HashMap> selectorderlytweyd = xlevelserviceMapper.selectorderlytweyd();
        for (int i=0;i<selectorderlytweyd.size();i++){
            selectorderlytweyd.get(i).put("SDnumber",0);
        }
        //三级应到
        List<HashMap> selectorderlythreeyd = xlevelserviceMapper.selectorderlythreeyd();
        for (int i=0;i<selectorderlythreeyd.size();i++){
            selectorderlythreeyd.get(i).put("SDnumber",0);
        }

        map.put("stair",selectorderlyoneyd);
        map.put("second",selectorderlytweyd);
        map.put("three",selectorderlythreeyd);
        return ResultUtil.setOK("success",map);
    }

}
