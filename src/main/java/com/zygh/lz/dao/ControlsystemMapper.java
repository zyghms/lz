package com.zygh.lz.dao;

import com.zygh.lz.entity.Controlsystem;

public interface ControlsystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Controlsystem record);

    int insertSelective(Controlsystem record);

    Controlsystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Controlsystem record);

    int updateByPrimaryKey(Controlsystem record);
}