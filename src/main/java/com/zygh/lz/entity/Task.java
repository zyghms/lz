package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private Date taskCreatetime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date taskFinishtime;

    private String taskTitle;

    private String taskDescribe;

    private String taskMoney;

    private String taskPlanMoney;

    private String taskDetail;

}