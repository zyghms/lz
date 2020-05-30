package com.zygh.lz.service;

import com.zygh.lz.entity.Message;
import com.zygh.lz.vo.ResultBean;

public interface MessageService {
    //查询所有消息列表
    ResultBean slectAllmessage(Integer messageState,Integer accpetId);
    //新增消息
    ResultBean addMessage(Message message);
    //修改消息 (逻辑删除)
    ResultBean updaMessage(Message message);
    //删除
    ResultBean delMessage(Integer id);
    //与问题任务表关联消息信息
    ResultBean selectAllByPid(Integer messagePid,Integer messageType);

}
