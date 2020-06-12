package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Infrastructure implements Serializable{
    private Integer sysInfrastructureId;

    private Integer sysRoadId;

    private String infrastructureName;

    private String infrastructureType;

    private String damage;

    private String capital;

    private String department;

    private String locationdetails;

}