package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Road implements Serializable {
    private Integer sysRoadId;

    private Integer sysUrbanId;

    private String roadName;

    private String roadBegin;

    private String roadOver;

    private String roadType;

    private String roadGps;

    private String roadGpsBegin;

    private String roadGpsEnd;


}