package com.zygh.lz.admin;

import java.util.Date;

public class admamera {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSrcEvtId() {
        return srcEvtId;
    }

    public void setSrcEvtId(String srcEvtId) {
        this.srcEvtId = srcEvtId == null ? null : srcEvtId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getEvtTypeNo() {
        return evtTypeNo;
    }

    public void setEvtTypeNo(String evtTypeNo) {
        this.evtTypeNo = evtTypeNo == null ? null : evtTypeNo.trim();
    }

    public String getSubEvtTypeNo() {
        return subEvtTypeNo;
    }

    public void setSubEvtTypeNo(String subEvtTypeNo) {
        this.subEvtTypeNo = subEvtTypeNo == null ? null : subEvtTypeNo.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId == null ? null : cameraId.trim();
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName == null ? null : cameraName.trim();
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash == null ? null : geohash.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getStaytime() {
        return staytime;
    }

    public void setStaytime(String staytime) {
        this.staytime = staytime == null ? null : staytime.trim();
    }

    public Date getPublicRecTime() {
        return publicRecTime;
    }

    public void setPublicRecTime(Date publicRecTime) {
        this.publicRecTime = publicRecTime;
    }

    public Date getPublicProTime() {
        return publicProTime;
    }

    public void setPublicProTime(Date publicProTime) {
        this.publicProTime = publicProTime;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds == null ? null : ds.trim();
    }

    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId == null ? null : gridId.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}