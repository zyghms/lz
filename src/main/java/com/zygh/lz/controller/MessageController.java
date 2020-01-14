package com.zygh.lz.controller;

import com.zygh.lz.admin.message;
import com.zygh.lz.service.messageService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private messageService messageService;

    //查询所有消息列表
    @GetMapping("slectAllmessage")
    public ResultBean slectAllmessage(Integer messageState,Integer accpetId){
        return messageService.slectAllmessage(messageState,accpetId);
    }

    //新增消息
    @PostMapping("addMessage")
    public ResultBean addMessage(message message){
        return messageService.addMessage(message);
    }
    //修改消息 (逻辑删除)
    @GetMapping("updaMessage")
    public ResultBean updaMessage(message message){
        return messageService.updaMessage(message);
    }
    //删除
    @GetMapping("delMessage")
    public ResultBean delMessage(Integer id){
        return messageService.delMessage(id);
    }
    //与问题任务关联的消息
    @GetMapping("selectAllByPid")
    public ResultBean selectAllByPid(Integer messagePid, Integer messageType){
        return messageService.selectAllByPid(messagePid,messageType);
    }

}
