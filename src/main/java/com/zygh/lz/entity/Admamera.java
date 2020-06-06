package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admamera implements Serializable{
    private Long id;

    private String srcEvtId;

    private String status;

    private String evtTypeNo;

    private String subEvtTypeNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date outTime;

    private String cameraId;

    private String cameraName;

    private Double lng;

    private Double lat;

    private String geohash;

    private String memo;

    private String staytime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publicRecTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publicProTime;

    private String ds;

    private Long interfaceId;

    private String areaCode;

    private String gridId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
}