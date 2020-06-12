package com.zygh.lz.dao;

import com.zygh.lz.entity.Cfrwinfo;

public interface CfrwinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cfrwinfo record);

    int insertSelective(Cfrwinfo record);

    Cfrwinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cfrwinfo record);

    int updateByPrimaryKey(Cfrwinfo record);
}