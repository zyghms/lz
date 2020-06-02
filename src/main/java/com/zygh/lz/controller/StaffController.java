package com.zygh.lz.controller;

import com.zygh.lz.entity.Xarea;
import com.zygh.lz.service.StaffService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {
    @Autowired
    private StaffService staffService;


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
        return staffService.selectStaffYdByAll(null, SectionId);
    }

    //在线民警
    @GetMapping("selectpoliceZx")
    public ResultBean selectpoliceZx(String station) throws Exception {
        return staffService.selectpoliceZx(station);
    }

    //查询该人的直系领导
    @GetMapping("selectStaffByid")
    public ResultBean selectStaffByid(Integer id) {
        return staffService.selectStaffByid(id);
    }

    //根据民警id查询该民警所负责的路段里程，上班时长
    @GetMapping("selectStaffXareaByid")
    public ResultBean selectStaffXareaByid(Integer id) throws Exception {
        return staffService.selectStaffXareaByid(id);
    }

    //根据民警id查询该民警所负责的路段
    @GetMapping("selectXareaByid")
    public ResultBean selectXareaByid(Integer id) throws Exception {
        return staffService.selectXareaByid(id);
    }

    //返回在线民警GPS
    @GetMapping("selectStaffZx")
    public ResultBean selectStaffZx(Integer id) {
        return staffService.selectStaffZx(id);
    }

    //查询昨日总警力
    @GetMapping("selecttotalforces")
    public ResultBean selecttotalforces() {
        return staffService.selecttotalforces();
    }

    //按大队查询昨日总警力
    @GetMapping("selecttotalforceszr")
    public ResultBean selecttotalforceszr() {
        return staffService.selecttotalforceszr();
    }

    //根据不同岗位查询在线人数
    @GetMapping("selectzaBystation")
    public ResultBean selectzaBystation(String station, String conment, String grid) {
        return staffService.selectzaBystation(station, conment, grid);
    }

    //查询其他在岗人
    @GetMapping("selectStaffByqita")
    public ResultBean selectStaffByqita() {
        return staffService.selectStaffByqita();
    }

    //以单位为结构显示信息列表
    @GetMapping("selectAllbytype")
    public ResultBean selectAllbytype(String battalion) {
        return staffService.selectAllbytype(battalion);
    }


    //根据岗位查询各大队在线民警
    @GetMapping("selectcountBysection")
    public ResultBean selectcountBysection(Xarea xarea) {
        return staffService.selectcountBysection(xarea);
    }

    //根据岗位查询各大队下各中队在线民警
    @GetMapping("selectcountBydetachment")
    public ResultBean selectcountBydetachment(Xarea xarea) {
        return staffService.selectcountBydetachment(xarea);
    }

    //根据岗位查询各中队在线民警详情
    @GetMapping("selectAllBysection")
    public ResultBean selectAllBysection(Xarea xarea) {
        return staffService.selectAllBysection(xarea);
    }
    //根据传入的id 查询人员详情
    @GetMapping("selectPersonDetail")
    public ResultBean selectPersonDetail(String  str ) {
        System.out.println(str);
        return staffService.selectPersonDetail(str);
    }

}
