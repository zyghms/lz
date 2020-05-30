package com.zygh.lz.dao;

import com.zygh.lz.entity.Controlsubsystem;

public interface ControlsubsystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Controlsubsystem record);

    int insertSelective(Controlsubsystem record);

    Controlsubsystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Controlsubsystem record);

    int updateByPrimaryKey(Controlsubsystem record);
}