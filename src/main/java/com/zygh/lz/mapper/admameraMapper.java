package com.zygh.lz.mapper;

import com.zygh.lz.admin.admamera;
import com.zygh.lz.admin.admameraWithBLOBs;

import java.util.List;

public interface admameraMapper {
    int deleteByPrimaryKey(Long id);

    int insert(admameraWithBLOBs record);

    int insertSelective(admameraWithBLOBs record);

    admameraWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(admameraWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(admameraWithBLOBs record);

    int updateByPrimaryKey(admamera record);

    //查询当天
    List<admameraWithBLOBs> selectAllByToday();
}