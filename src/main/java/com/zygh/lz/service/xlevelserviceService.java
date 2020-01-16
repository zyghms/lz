package com.zygh.lz.service;

import com.zygh.lz.admin.Xlevelservice;
import com.zygh.lz.vo.ResultBean;

import java.util.List;
import java.util.Map;

public interface xlevelserviceService {

    //等级勤务所有应到人数
    ResultBean selectorderlyAll(String sectionName);

    //一级勤务按大队应到人数
    ResultBean selectorderlydjyd(String sectionName);

    ResultBean selectxleveBydj(Integer hierarchy,String sectionName);
}
