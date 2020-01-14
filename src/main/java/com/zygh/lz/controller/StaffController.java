package com.zygh.lz.controller;

import com.zygh.lz.admin.Staff;
import com.zygh.lz.service.staffService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {
    @Autowired
    private staffService staffService;


    //默认人
    @GetMapping("selectdefault")
    public ResultBean selectdefault(String probleDetail) {
        return staffService.selectdefault(probleDetail);
    }

    //默认大队
    @GetMapping("selectStaffBySectionName")
    public ResultBean selectStaffBySectionName(Integer staffid) {
        return staffService.selectStaffBySectionName(staffid);
    }

    //查询在岗人数
    @GetMapping("selectStaffByzg")
    public ResultBean selectStaffByzg(Integer sectionId, Integer sectionPid, String sectionName) {
        return staffService.selectStaffByzg(sectionId, sectionPid, sectionName);
    }

    @GetMapping("selectStaffByName")
    public ResultBean selectStaffByName(String name, String staffHierarchy) {
        return staffService.selectStaffByName(name, staffHierarchy);
    }

    //分层级查询在岗人数
    @GetMapping("selectStaffInfoByid")
    public ResultBean selectStaffInfoByid(Integer id) {
        return staffService.selectStaffInfoByid(id);
    }

    //应到民警
    @GetMapping("selectStaffYdByAll")
    public ResultBean selectStaffYdByAll(Integer SectionId) {
        return staffService.selectStaffYdByAll(null,SectionId);
    }

    //在线民警
    @GetMapping("selectpoliceZx")
    public ResultBean selectpoliceZx() {
        return staffService.selectpoliceZx();
    }

    //查询该人的直系领导
    @GetMapping("selectStaffByid")
    public ResultBean selectStaffByid(Integer id) {
        return staffService.selectStaffByid(id);
    }

    //根据民警id查询该民警所负责的路段
    @GetMapping("selectStaffXareaByid")
    public ResultBean selectStaffXareaByid(Integer id){
       return staffService.selectStaffXareaByid(id);
    }
}
