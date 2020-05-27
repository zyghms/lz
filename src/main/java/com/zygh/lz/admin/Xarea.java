package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Xarea implements Serializable{
    private Integer id;

    private String name;

    private String station;

    private String type;

    private String battalion;

    private String detachment;

    private String gridding;

    private String rank;

    private String leader;

    private String conment;

    private String centre;

    private String gps;

    private String stafftype;

    //应到人员
    public List<Staff> staff ;

    //实到人
    public  List<Patrolrecord> number;

    private Integer sectionZid;

    private Integer sectionDid;


}