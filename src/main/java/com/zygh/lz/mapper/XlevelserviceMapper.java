package com.zygh.lz.mapper;

import com.zygh.lz.admin.Xlevelservice;

public interface XlevelserviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Xlevelservice record);

    int insertSelective(Xlevelservice record);

    Xlevelservice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Xlevelservice record);

    int updateByPrimaryKey(Xlevelservice record);
}