package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subsystem implements Serializable {
    private Integer sysSubsystemId;

    private String subsystemName;

    private String icon;

    private String sref;

    private Integer sysProjectId;

    private Integer title;

    private Integer subsystemSort;

    public List<Menu> menuList ;











}