package com.zygh.lz.dao;

import com.zygh.lz.entity.Message;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface MessageMapper {
    int deleteByPrimaryKey(Integer sysMessageId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer sysMessageId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    //消息列表
    List<Message> slectAllmessage(@Param("messageState") Integer messageState,@Param("accpetId") Integer accpetId);

    //消息与问题任务关联
    List<Message> selectProblemByPid(Integer messagePid);
    List<Message> selectTaskByPid(Integer messagePid);
}