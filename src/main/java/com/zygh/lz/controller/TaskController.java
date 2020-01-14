package com.zygh.lz.controller;

import com.zygh.lz.admin.Task;
import com.zygh.lz.service.taskService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    private taskService taskService;

    //新增
    @PostMapping("addTask")
    public ResultBean addTask(Task task) {
        return taskService.addTask(task);
    }

    //修改
    @GetMapping("updaTask")
    public ResultBean updaTask(Task task) {
        return taskService.updaTask(task);
    }

    //删除
    @GetMapping("delTask")
    public ResultBean delTask(Integer id) {
        return taskService.delTask(id);
    }

    //查询所有任务
    @GetMapping("selectAllTask")
    public ResultBean selectAllTask() {
        return taskService.selectAllTask();
    }

    //关联问题查询展示任务列表(待办任务列表)
    @GetMapping("selectAllTP")
    public ResultBean selectAllTP(Integer acceptStaffId) {
        return taskService.selectAllTP(acceptStaffId);
    }

    //我的任务
    @GetMapping("selectAllByAccept")
    public ResultBean selectAllByAccept(Integer acceptStaffId, String taskState,String taskTitle,String taskCreatetime,String taskFinishtime) {
        return taskService.selectAllByAccept(acceptStaffId, taskState,taskTitle,taskCreatetime,taskFinishtime);
    }

    //查询默认维修队的人
    @GetMapping("selectStaffNameByProblem")
    public ResultBean selectStaffNameByProblem(String type) {
        return taskService.selectStaffNameByProblem(type);
    }

    //统计
    @GetMapping("selectTaskByCount")
    public ResultBean selectTaskByCount(String taskState, Integer sectionId, String time) {
        return taskService.selectTaskByCount(taskState, sectionId, time);
    }

    //统计
    @GetMapping("selectTPByNum")
    public ResultBean selectTPByNum(String taskState, Integer sectionId, String time, String staffHierarchy, String staffName) {
        return taskService.selectTPByNum(taskState, sectionId, time, staffHierarchy, staffName);
    }

    //批量删除
    @GetMapping("deleteTaskById")
    public ResultBean deleteTaskById(int[] array){
        return taskService.deleteTaskById(array);
    }
    //查询本大队的任务
    @GetMapping("selectTaskBySection")
    public ResultBean selectTaskBySection(Integer sectionId,String taskTitle,String taskCreatetime,String taskFinishtime){return taskService.selectTaskBySection(sectionId,taskTitle,taskCreatetime,taskFinishtime);}
    //查询任务细节为空的任务数据
    @GetMapping("selectTaskDescribe")
    public ResultBean selectTaskDescribe(Integer sectionId,String taskTitle,String taskCreatetime,String taskFinishtime) {return taskService.selectTaskDescribe(sectionId,taskTitle,taskCreatetime,taskFinishtime);}
}
