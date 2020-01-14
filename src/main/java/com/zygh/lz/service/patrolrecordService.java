package com.zygh.lz.service;

import com.zygh.lz.admin.Patrolrecord;
import com.zygh.lz.admin.Problem;
import com.zygh.lz.vo.ResultBean;

import java.util.HashMap;
import java.util.List;

//巡查
public interface patrolrecordService {
    //新增巡查记录
    ResultBean addPatrolrecord(Patrolrecord patrolrecord);

    //修改巡查记录
    ResultBean updatePatrolrecord(Patrolrecord patrolrecord);

    //删除巡查记录
    ResultBean delPatrolrecord(Integer id);

    //根据道路类型、部门查询所有巡查信息
    ResultBean selectByRoadtype(Integer staffid,String beginTime, String endTime);

    //根据层级显示相对信息（一级路长）
    ResultBean selectByhierarchy(String staffHierarchy, Integer sysSectionId, String roadType);

    //根据层级显示相对信息（二级路长）
    ResultBean selectBysysStaffId(Integer staffid, String roadType);

    //根据sysStaffId修改已打分
    ResultBean updatePatrolrecordBysysStaffId(Patrolrecord patrolrecord);

    //查询未打分的
    ResultBean selectBypatrolstate(Integer staffid, String roadType);

    //统计巡查
    ResultBean countPatrolrecord(String startTime, String endTime) throws Exception;

    //查询所有巡查记录
    ResultBean selectAllPatrolrecord();

    //查询所有巡查记录的视频
    ResultBean selectVideo();

    //根据开始时间，结束时间、道路名称查询巡查记录
    ResultBean selectDim(String roadName,String beginTime,String endTime);

    //查询个人
    ResultBean selectByStaff(Integer SysStaffId,String beginTime,String endTime);
    //手机端
    ResultBean selectByStaffapp(Integer SysStaffId,String beginTime,String endTime);

    //查询各个大队的巡查记录个数
    ResultBean selectRecordNum(String startDate, String endDate);

    //登录用户负责道路列表
    ResultBean selectByStaffId(Integer StaffId);

    ResultBean delectPatrolrecordById(int[] array);

    //根据id查询该人的巡查记录
    ResultBean selectAllPatrolrecordById(Integer id);

    public List<HashMap> theDaySum(String battalion) throws Exception;
    public List<HashMap> typeSum(String battalion) throws Exception;
}
