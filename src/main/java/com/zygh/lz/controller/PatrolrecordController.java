package com.zygh.lz.controller;

import com.zygh.lz.admin.Patrolrecord;
import com.zygh.lz.mapper.PatrolrecordMapper;
import com.zygh.lz.service.patrolrecordService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 巡查记录
 */
@RestController
public class PatrolrecordController {
    @Autowired
    private patrolrecordService patrolrecordService;
    @Autowired
    private PatrolrecordMapper patrolrecordMapper;

    //查询所有巡查记录
    @GetMapping("selectAllPatrolrecord")
    public ResultBean selectAllPatrolrecord() {
        return patrolrecordService.selectAllPatrolrecord();
    }

    //查询所有巡查记录视频
    @GetMapping("selectVideo")
    public ResultBean selectVideo() {
        return patrolrecordService.selectVideo();
    }

    //新增巡查记录
    @PostMapping("addPatrolrecord")
    public ResultBean addPatrolrecord(Patrolrecord patrolrecord) {
        return patrolrecordService.addPatrolrecord(patrolrecord);
    }

    //根据开始时间，结束时间、道路名称查询巡查记录
    @GetMapping("selectDim")
    public ResultBean selectDim(String roadName, String beginTime, String endTime) {
        return patrolrecordService.selectDim(roadName, beginTime, endTime);
    }

    //根据道路类型、部门查询所有巡查信息(分层级查询)
    @GetMapping("selectByRoadtype")
    public ResultBean selectByRoadtype( Integer staffid, String beginTime, String endTime) {
        return patrolrecordService.selectByRoadtype( staffid, beginTime, endTime);
    }

    //根据时间查询个人巡查记录
    @GetMapping("selectByStaffs")
    public ResultBean selectByStaffs(Integer SysStaffId, String beginTime, String endTime) {
        return patrolrecordService.selectByStaff(SysStaffId, beginTime, endTime);
    }
    //根据时间查询个人巡查记录app
    @GetMapping("selectByStaff")
    public ResultBean selectByStaff(Integer SysStaffId, String beginTime, String endTime) {
        return patrolrecordService.selectByStaffapp(SysStaffId, beginTime, endTime);
    }

    //查询各个大队的巡查记录个数
    @GetMapping("selectRecordNum")
    public ResultBean selectRecordNum(String startDate, String endDate) {
        return patrolrecordService.selectRecordNum(startDate, endDate);
    }

    //巡查统计
    @GetMapping("countPatrolrecord")
    public ResultBean countPatrolrecord(String startTime, String endTime) throws Exception {
        return patrolrecordService.countPatrolrecord(startTime, endTime);
    }

    //根据登录用户负责道路列表
    @GetMapping("selectByStaffId")
    public ResultBean selectByStaffId(Integer staffId) {
        return  patrolrecordService.selectByStaffId(staffId);
    }

    //查询最后一条巡查记录
    @GetMapping("selectBylast")
    public Patrolrecord selectBylast(){
        return patrolrecordMapper.selectBylast();
    }

    //修改巡查记录
    @GetMapping("updatePatrolrecord")
    public ResultBean updatePatrolrecord(Patrolrecord patrolrecord){
        return patrolrecordService.updatePatrolrecord(patrolrecord);
    }

    //删除巡查记录
    @GetMapping("delPatrolrecord")
    public ResultBean delPatrolrecord(Integer id){
        return patrolrecordService.delPatrolrecord(id);
    }

    //批量删除
    @GetMapping("delectPatrolrecordById")
    public ResultBean delectPatrolrecordById(int[] array){
        return patrolrecordService.delectPatrolrecordById(array);
    }

    //根据id查询该人的巡查记录
    @GetMapping("selectAllPatrolrecordById")
    public ResultBean selectAllPatrolrecordById(Integer id){
        return patrolrecordService.selectAllPatrolrecordById(id);
    }

    //查询当天高峰岗和固定岗 实到、应到人数
    @GetMapping("theDaySum")
    public List<HashMap> theDaySum(String battalion)throws Exception{
        return patrolrecordService.theDaySum(battalion);
    }
    @GetMapping("typeSum")
    public List<HashMap> typeSum(String battalion)throws Exception{
        return patrolrecordService.typeSum(battalion);
    }

}
