package com.zygh.lz.dao;
import com.zygh.lz.entity.Admamera;
import com.zygh.lz.entity.AdmameraWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

import  java.util.List;

@Mapper
public interface AdmameraMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdmameraWithBLOBs record);

    int insertSelective(AdmameraWithBLOBs record);

    AdmameraWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdmameraWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AdmameraWithBLOBs record);

    int updateByPrimaryKey(Admamera record);

    //查询当天
    List<AdmameraWithBLOBs> selectAllByToday();
}