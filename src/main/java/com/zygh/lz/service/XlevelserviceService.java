package com.zygh.lz.service;

import com.zygh.lz.entity.Xlevelservice;
import com.zygh.lz.vo.ResultBean;

import java.util.List;

public interface XlevelserviceService {

    //等级勤务所有应到人数
    ResultBean selectorderlyAll(String sectionName);

    //一级勤务按大队应到人数
    ResultBean selectorderlydjyd(String sectionName);

    ResultBean selectxleveBydj(Integer hierarchy,String sectionName);

    ResultBean selectXlevedJ(Integer hierarchy);

    //查询特殊勤务左侧列表
    ResultBean selectSpecialService();

    ResultBean delSpecialService(Integer id);

    ResultBean addSpecialService(Xlevelservice xlevelservice);

    ResultBean updateSpecialService(Xlevelservice xlevelservice);

    List<Xlevelservice> selectXlevelservice(Integer type);
<<<<<<< HEAD
=======


>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
}

