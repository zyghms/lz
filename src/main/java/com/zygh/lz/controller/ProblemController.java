package com.zygh.lz.controller;

import com.zygh.lz.admin.Problem;
import com.zygh.lz.service.problemService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProblemController {
    @Autowired
    private problemService problemService;

    //新增
    @PostMapping("addProblem")

    public ResultBean addProblem(Problem problem){
        return problemService.addProblem(problem);
    }
    //修改
    @GetMapping("updateProblem")
    public ResultBean updateProblem(Problem problem){
        return problemService.updateProblem(problem);
    }
    //删除
    @GetMapping("delProblem")
    public ResultBean delProblem(Integer id){
        return problemService.delProblem(id);
    }
    //根据人查询全部问题  (所有问题)
    @GetMapping("selectProblemByStaffId")
    public ResultBean selectProblemByStaffId(Integer staffReportId){
        return problemService.selectProblemByStaffId(staffReportId);
    }
    //查询问题解决未解决个数
    @GetMapping("selectProblemByRosove")
    public ResultBean selectProblemByRosove(Integer id){
        return problemService.selectProblemByRosove(id);
    }
    //根据道路类型查询问题
    @GetMapping("selectAllByRoadType")
    public ResultBean selectAllByRoadType(Integer sysStaffId,String roadType){
        return problemService.selectAllByRoadType(sysStaffId,roadType);
    }

    //问题模糊查询分层
    @GetMapping("selectDims")
    public ResultBean selectDims(HttpServletRequest request,Integer sysStaffId, String roadName, String staffName,
                                 String sectionName, String problemStrat,
                                 String staffHierarchy, String beginTime,
                                 String endTime, String problemCheck, String problemDetail){

        return problemService.selectDims(request,sysStaffId,roadName,staffName,sectionName,problemStrat,staffHierarchy,beginTime,endTime,problemCheck,problemDetail);
    }
    //根据人查询问题
    @GetMapping("selectDimStaff")
    public ResultBean selectDimStaff(Integer sysStaffId,String beginTime,String endTime){
        return problemService.selectDimStaff(sysStaffId,beginTime,endTime);
    }

    //领导管理的人所查询问题的模糊查询
    @GetMapping("selectDimStaffOther")
    public ResultBean selectDimStaffOther(Integer staffId, String beginTime, String endTime) {
        return problemService.selectDimStaffOther(staffId,beginTime,endTime);
    }

    //巡查记录个数统计
    @GetMapping("selectDimAll")
    public ResultBean selectDimAll(String startTime, String endTime){
        return problemService.selectDimAll(startTime,endTime);
    }
    //巡查记录个数统计
    @GetMapping("selectDimAllSy")
    public ResultBean selectDimAllSy(){
        return problemService.selectDimAllSy();
    }

    //按条件查询没有核查的问题
    @GetMapping("selectProblem")
    public ResultBean selectProblem(Integer staffAcceptId, String roadName,String problemStrat){

        return problemService.selectProblem(staffAcceptId, roadName,problemStrat);
    }

    //查询所有除去已完成以外的多有问题
    @GetMapping("selectProblemByState")
    public ResultBean selectProblemByState(){
        return problemService.selectProblemByState();
    }

    //问题统计
    @GetMapping("selectProblemByCount")
    public ResultBean selectProblemByCount(String problemState,Integer sectionId, String time){
        return problemService.selectProblemByCount(problemState,sectionId,time);
    }

    //根据主键id查询问题详情
    @GetMapping("selectProblemByid")
    public ResultBean selectProblemByid(Integer id,Integer type){
        return problemService.selectProblemByid(id,type);
    }

    //查询所有不能解决的问题
    @GetMapping("selectProbleByStart")
    public ResultBean selectProbleByStart(String problemState){
        return problemService.selectProbleByStart(problemState);
    }

    //模糊查询
    @GetMapping("selectByProblemdetail")
    public ResultBean selectByProblemdetail(String typeKey, String problemStrat, String beginTime, String endTime){
        return problemService.selectByProblemdetail(typeKey,problemStrat,beginTime,endTime);
    }

    //批量删除
    @GetMapping("deleteByProblemid")
    public ResultBean deleteByProblemid(int[] array){
        return problemService.deleteByProblemid(array);
    }

    //统计所有
    @GetMapping("selectAllCount")
    public ResultBean selectAllCount(){
        return problemService.selectAllCount();
    }
}
