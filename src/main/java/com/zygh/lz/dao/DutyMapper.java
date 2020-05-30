package com.zygh.lz.dao;

import com.zygh.lz.entity.Duty;

import java.util.List;

public interface DutyMapper {
    int deleteByPrimaryKey(Integer sysDutyId);

    int insert(Duty record);

    int insertSelective(Duty record);

    Duty selectByPrimaryKey(Integer sysDutyId);

    int updateByPrimaryKeySelective(Duty record);

    int updateByPrimaryKey(Duty record);

    //查询所有
    List<Duty> selectAllDuty();

    //模糊查询
    List<Duty> seleteDimDuty(Duty duty) ;
}