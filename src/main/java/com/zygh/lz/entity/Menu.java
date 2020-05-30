package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private Integer sysMenuId;

    private Integer parentId;

    private String menuUrl;

    private String menuIcon;

    private Integer menuSort;

    private String menuName;

    private Integer sysSubsystemId;
    
}