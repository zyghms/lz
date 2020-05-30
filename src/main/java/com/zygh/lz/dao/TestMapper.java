package com.zygh.lz.dao;

import com.zygh.lz.entity.Test;

import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKeyWithBLOBs(Test record);

    List<Test> selectTestAll();
}