package com.zygh.lz.dao;

import com.zygh.lz.entity.Rank;

public interface RankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rank record);

    int insertSelective(Rank record);

    Rank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rank record);

    int updateByPrimaryKey(Rank record);
}