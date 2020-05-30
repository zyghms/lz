package com.zygh.lz.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gps implements Serializable {
    private Integer sysGpsId;

    private String gpsX;

    private String gpsY;

    private String gpsZ;

    private String gpsTime;

    private Integer sysStaffId;

    private Integer sysPatrolRecordId;
    
}