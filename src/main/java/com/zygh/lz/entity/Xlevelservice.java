package com.zygh.lz.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Xlevelservice implements Serializable{
    private Integer id;

    private String callsign;

    private String place;

    private Integer subofficeId;

    private Integer number;

    private Integer sectionId;

    private String ondutytime;

    private String location;

    private String hierarchy;

    private String conment;

    private Integer state; //勤务类型 0-等级勤务 1-重要警卫任务 2-应急管理任务 3-专项警卫任务 4-大型活动 5-其他警务

    private Integer zt; //1-正常 2-删除默认正常
    private List<Xlevelservice> sectionList ;

}