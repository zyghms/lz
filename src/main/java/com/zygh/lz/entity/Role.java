package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable{
    private Integer sysRoleId;

    private String roleName;

    private String roleDetails;

    private String subsystemUsetype;

    private String menuUsetype;

    private String controlUsetype;          //控件使用状态

    private String controlUsetypeName ;

    private String menuUsetypeName ;        //菜单使用状态名称

    private String subsystemUsetypeName ;








}