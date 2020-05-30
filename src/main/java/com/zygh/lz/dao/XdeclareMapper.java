package com.zygh.lz.dao;

import com.zygh.lz.entity.Xdeclare;

public interface XdeclareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Xdeclare record);

    int insertSelective(Xdeclare record);

    Xdeclare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Xdeclare record);

    int updateByPrimaryKey(Xdeclare record);
}