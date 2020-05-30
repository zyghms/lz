package com.zygh.lz.dao;

import com.zygh.lz.entity.Notice;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer sysNoticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer sysNoticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);


    List<Notice> selectAllNotice(Integer sectionId);
    //查询所有通知
    List<Notice> selectNotrce();
}