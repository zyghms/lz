package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem implements Serializable {
    private Integer problemId;

    private String problemNum;

    private Integer problemType;

    private String problemDescribe;

    private String problemState;

    private String problemPicture;

    private String problemVideo;

    private String problemGpsX;

    private String problemGpsY;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date problemDate;

    private String problemCheck;

    private String problemDetail;

    private Integer sysPatrolRecordId;

    private Integer sysStaffId;

    private String roadName;

    private Integer acceptId;

    private Staff staff;

    private String sectionName;

    private String sectionPid;

    private Road road;                      //道路

    private String section;

}