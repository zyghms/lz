package com.zygh.lz.dao;

import com.zygh.lz.entity.Infrastructure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InfrastructureMapper {
    int deleteByPrimaryKey(Integer sysInfrastructureId);

    int insert(Infrastructure record);

    int insertSelective(Infrastructure record);

    Infrastructure selectByPrimaryKey(Integer sysInfrastructureId);

    int updateByPrimaryKeySelective(Infrastructure record);

    int updateByPrimaryKey(Infrastructure record);

    List<Infrastructure> selectInfrastructureByInfo(@Param("type") String type,@Param("roadName") String roadName);
}