package com.zygh.lz.dao;

import com.zygh.lz.entity.Xrelation;
import org.apache.ibatis.annotations.Param;

public interface XrelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Xrelation record);

    int insertSelective(Xrelation record);

    Xrelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Xrelation record);

    int updateByPrimaryKey(Xrelation record);

    Xrelation findXrealationByid(Integer staffId,Integer xareaId);

    int deleteById(@Param("staffId")  Integer staffId, @Param("xareaId")  Integer xareaId);
}