package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patrolrecord implements Serializable {
    private Integer sysPatrolRecordId;

    private Integer sysStaffId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date patrolRecordBegintime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date patrolRecordEndtime;

    private String patrolRecordPicture;

    private String patrolRecordVideo;

    private String patrolRecordGps;

    private String patrolRecordDetail;

    private String roadName;

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

    private Integer sectionPid;

    private String sectionName;

    private String strength;

    private String changeShifts;

    private Gps gpsdw;

    private String sectionPosition;

    private String sectionTel;

    private String sectionPerson;

    public List<Gps> gpsList ;

    public List<Problem> problemList ;



}