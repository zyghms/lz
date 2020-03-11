package com.zygh.lz.admin;

public class Test {
    private Integer id;

    private String name;

    private String gps;

    private String po;

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps == null ? null : gps.trim();
    }
}