package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class admamera implements Serializable{
    private Long id;

    private String srcEvtId;

    private String status;

    private String evtTypeNo;

    private String subEvtTypeNo;

    private Date inTime;

    private Date outTime;

    private String cameraId;

    private String cameraName;

    private Double lng;

    private Double lat;

    private String geohash;

    private String memo;

    private String staytime;

    private Date publicRecTime;

    private Date publicProTime;

    private String ds;

    private Long interfaceId;

    private String areaCode;

    private String gridId;

    private Date gmtCreate;

    private Date gmtModified;
}