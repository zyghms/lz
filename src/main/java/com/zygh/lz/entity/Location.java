package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable{
    private Integer id;

    private Integer sysStaffId;

    private String gpsX;

    private String gpsY;

    private Date time;

}