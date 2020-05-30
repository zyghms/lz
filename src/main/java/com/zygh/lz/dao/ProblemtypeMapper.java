package com.zygh.lz.dao;

import com.zygh.lz.entity.Problemtype;

import java.util.List;

public interface ProblemtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Problemtype record);

    int insertSelective(Problemtype record);

    Problemtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Problemtype record);

    int updateByPrimaryKey(Problemtype record);

    Problemtype selectStaffNameByType(String type);

    List<Problemtype> selectAllByProblemtype();


}