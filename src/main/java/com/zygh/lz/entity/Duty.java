package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Duty implements Serializable{
    private Integer sysDutyId;

    private String sutyRoad;

    private Integer sysSectionId;

    private Integer sysRoadId;

    private Integer fenguanstaffId;

    private Integer totalstaffId;

    private Integer onestaffId;

    private Integer twostaffId;

    private String fenguanName ;        //分管路长姓名

    private String totalName ;          //总路长姓名

    private String oneName ;            //一級路长姓名

    private String sectionName;         //部门名称

    private String roadName;            //道路名称

    private String twoName;

}