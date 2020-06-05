package com.zygh.lz.service.impl;

import com.zygh.lz.dao.StaffMapper;
import com.zygh.lz.dao.XrelationMapper;
import com.zygh.lz.entity.Xrelation;
import com.zygh.lz.service.XrelationService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XrelationServiceImpl implements XrelationService {

    @Autowired
    private XrelationMapper xrelationMapper;

//    @Autowired
//    private StaffMapper staffMapper;

    @Override
    public ResultBean insertXrelation(Xrelation xrelation) {
        return ResultUtil.execOp(xrelationMapper.insertSelective(xrelation),"新增");
    }

    @Override
    public ResultBean updateXrelation(Xrelation xrelation) {
        return ResultUtil.execOp(xrelationMapper.updateByPrimaryKeySelective(xrelation),"修改");
    }

    @Override
    public ResultBean deleteXrelatonByid(Integer staffId,Integer xareaId) {
//       int result=  xrelationMapper.deleteById(staffId,xareaId);
        return ResultUtil.execOp(xrelationMapper.deleteById(staffId,xareaId),"删除");
    }
}
