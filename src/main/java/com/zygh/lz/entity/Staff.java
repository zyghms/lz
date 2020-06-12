package com.zygh.lz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff  implements Serializable{
    private Integer sysStaffId;

    private String staffName;

    private String staffPost;

    private String staffTel;

    private Integer sysGpsId;

    private Integer sysSectionId;

    private Integer sysRoleId;

    private String staffSex;

    private String staffPhoto;

    private String staffPassword;

    private String staffHierarchy;

    private Integer stafffPid;

    private String staffNum;

    private String staffIdcard;

    private String staffOnline;

    private List<Staff> staffList ;

    private String sectionName;

    private String stafftype;

    private String strength;

    private String changeShifts;

    private Gps gps;

    private Xarea xarea;

    private List<Xarea> xareaList;

    private String cellphonenumber;

    //时长
    private String duration;
    //距离
    private Double juli;

    //大队
    private String battalion;

    //上岗时间
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date patrolRecordBegintime;

    private Integer sectionpid;

    private Patrolrecord patrolrecord;









































}