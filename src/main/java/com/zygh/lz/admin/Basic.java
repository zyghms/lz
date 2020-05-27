package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basic implements Serializable{
    private Integer sysBasicId;             //基础设施id

    private String basicCategory;           //大类

    private String basicAttribute;          //小类

    private String basicIp;                 //ip地址

    private String basicModel;              //品牌型号

    private String basicBrandname;          //品牌名称

    private String basicEnvironment;        //部署环境

    private String basicNumber;             //序号

    private String basicDate;               //建设时间

    private String basicWay;                //发布方式

    private String basicNum;                //模块数量

    private String basicElectricity;        //供电方式

    private String basicDatasource;         //数据源

    private String basicPrecision;          //经度

    private String basicLatitude;           //纬度

    private String basicLocation;           //地理位置

    private String basicUse;                //主要用途

    private String basicNormal;             //是否正常

    private String basicEnable;             //是否启用

    private Integer sysDutyId;              //道路责任明细id

    private String basicAccount;            //账号

    private String basicPassword;           //密码

    private Road road;                      //道路

    private Duty duty;                      //道路责任明细

    private Staff staff;                    //路长

    private Section section;                //部门
}