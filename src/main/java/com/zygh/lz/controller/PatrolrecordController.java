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

    //查询当天日常勤务各时间段 实到、应到人数
    @GetMapping("theDaySum")
    public List<HashMap> theDaySum(String battalion)throws Exception{
        return patrolrecordService.theDaySum(battalion);
    }
    //统计各个岗位应到实到人
    @GetMapping("typeSum")
    public List<HashMap> typeSum(String battalion)throws Exception{
        return patrolrecordService.typeSum(battalion);
    }

    //查询当天各大队各中队日常勤务各时间段 实到、应到人数
    @GetMapping("countZD")
    public List<HashMap> countZD(String battalion)throws Exception{
        return patrolrecordService.countZD(battalion);
    }

    //统计夜间各个岗位应到实到人
    @GetMapping("yXtypeSum")
    public List<HashMap> yXtypeSum(String battalion)throws Exception{
        return patrolrecordService.yXtypeSum(battalion);
    }

    //按时间段统计日间各个岗位应到实到人
    @GetMapping("typeSumByTime")
    public List<HashMap> typeSumByTime(String battalion)throws Exception{
        return patrolrecordService.typeSumByTime(battalion);
    }

    //查询区域内在线人的点位
    @GetMapping("findNowByGps")
    public List<HashMap> findNowByGps(double[] lon, double[] lat) {

        return patrolrecordService.findNowByGps(lon,lat);
    }

    //根据大队名称查询在人GPS /热力图
    @GetMapping("findNowStaffBySection")
    public List<HashMap> findNowStaffBySection(String time,String battalion,Integer type){
        return patrolrecordService.findNowStaffBySection(time,battalion,type);
    }

    //根据大队名称统计在线、应到人数
    @GetMapping("findStaffSum")
    public HashMap findStaffSum(String time,String battalion,Integer type){
        return patrolrecordService.findStaffSum(time,battalion,type);
    }

    //判断是否在圆内
    @GetMapping("findCircleByGps")
    public List<HashMap> findCircleByGps(double circleX, double circleY,double r){
        return patrolrecordService.findCircleByGps(circleX,circleY,r);
    }

    //根据个人id查询改人当天是否签到
    @GetMapping("selectinfoByid")
    public ResultBean selectinfoByid(Integer id){
        return patrolrecordService.selectinfoByid(id);
    }

    //热力图在线警力
    @GetMapping("heatMap")
    public List<HashMap> heatMap(){
        return patrolrecordService.heatMap();
    }

    //热力图应到部署警力
    @GetMapping("heatMapYD")
    public List<HashMap> heatMapYD(){
        return patrolrecordService.heatMapYD();
    }

 }
