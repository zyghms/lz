package com.zygh.lz.dao;

import com.zygh.lz.entity.Notice;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer sysNoticeId);

    int insert(Notice notice);

    int insertSelective(Notice notice);

    Notice selectByPrimaryKey(Integer sysNoticeId);

    int updateByPrimaryKeySelective(Notice notice);

    int updateByPrimaryKey(Notice notice);


    List<Notice> selectAllNotice(Integer sectionId);
    //查询所有通知
    List<Notice> selectNotrce();


}