package com.zygh.lz.admin;

public class admameraWithBLOBs extends admamera {
    private String ossurl;

    private String shortVideoCode;

    public String getOssurl() {
        return ossurl;
    }

    public void setOssurl(String ossurl) {
        this.ossurl = ossurl == null ? null : ossurl.trim();
    }

    public String getShortVideoCode() {
        return shortVideoCode;
    }

    public void setShortVideoCode(String shortVideoCode) {
        this.shortVideoCode = shortVideoCode == null ? null : shortVideoCode.trim();
    }
}