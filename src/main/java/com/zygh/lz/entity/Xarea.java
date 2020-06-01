package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import  java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Xarea implements Serializable{
    public Integer id;

    public String name;

    public String station;

    public String type;

    public String battalion;

    public String detachment;

    public String gridding;

    public String rank;

    public String leader;

    public String conment;

    public String centre;

    public String gps;

    public String stafftype;

    //应到人员
    public List<Staff> staff ;

    //实到人
    public  List<Patrolrecord> number;

    public Integer sectionZid;

    public Integer sectionDid;

    public Date StartTime;  //开始时间

    public  Date EndTime; //结束时间


}