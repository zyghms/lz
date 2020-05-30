package com.zygh.lz.dao;

import com.zygh.lz.entity.Video;

import java.util.List;

public interface VideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Video> selectAllVideo();
    List<Video> selectAllVideode();
}