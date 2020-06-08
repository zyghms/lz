package com.zygh.lz.service.impl;

import com.zygh.lz.dao.SptypeMapper;
import com.zygh.lz.dao.XlevelserviceMapper;
import com.zygh.lz.entity.Sptype;
import com.zygh.lz.service.SptypeService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SptypeServiceImpl implements SptypeService {

    @Autowired
    private SptypeMapper sptypeMapper;
    @Autowired
    private XlevelserviceMapper xlevelserviceMapper;


    @Override
    public ResultBean delSptype(Integer id) {
        return ResultUtil.execOp(sptypeMapper.delSptype(id),"删除");


    }

    @Override
    public ResultBean addSptype(Sptype sptype){
        return ResultUtil.execOp(sptypeMapper.insertSelective(sptype),"新增");

    }

    @Override
    public ResultBean updateSptype(Sptype  sptype) {
        return ResultUtil.execOp(sptypeMapper.updateByPrimaryKey(sptype),"修改");

    }
    @Override
    public List<Sptype> selectSptypeInfo(String  battalion) {
        return sptypeMapper.selectSptypeInfo(battalion);

    }



}
