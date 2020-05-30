package com.zygh.lz.controller;

import com.zygh.lz.entity.Notice;
import com.zygh.lz.service.NoticeService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //新增公告
    @PostMapping("addNotice")
    public ResultBean addNotice(Notice notice){
        return noticeService.addNotice(notice);
    }
    //修改公告
    @GetMapping("updaNotice")
    public ResultBean updaNotice(Notice notice){
        return noticeService.updaNotice(notice);
    }
    //删除公告
    @GetMapping("delNotice")
    public ResultBean delNotice(Integer id){
        return noticeService.delNotice(id);
    }
    //根据接受部门查询所有公告
    @GetMapping("selectAllNotice")
    public ResultBean selectAllNotice(Integer sectionId){
        return noticeService.selectAllNotice(sectionId);
    }

    //查询所有公告
    @GetMapping("selectNotice")
    public ResultBean selectNotice() {
        return noticeService.selectNotice();
    }
}
