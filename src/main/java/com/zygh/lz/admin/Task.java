package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable{
    private Integer sysTaskId;

    private String taskNum;

    private Integer sysProblemId;

    private Integer launchStaffId;

    private Integer acceptStaffId;

    private String taskState;

    private Date taskCreatetime;

    private Date taskFinishtime;

    private String taskTitle;

    private String taskDescribe;

    private String taskMoney;

    private String taskPlanMoney;

    private String taskDetail;

}