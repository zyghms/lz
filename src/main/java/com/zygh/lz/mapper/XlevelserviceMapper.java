package com.zygh.lz.mapper;

import com.zygh.lz.admin.Xlevelservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface XlevelserviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Xlevelservice record);

    int insertSelective(Xlevelservice record);

    Xlevelservice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Xlevelservice record);

    int updateByPrimaryKey(Xlevelservice record);

    //等级勤务所有应到人数
    int selectorderlyAll();

    //一级勤务按大队应到人数
    List<HashMap> selectorderlyoneyd();

    //二级勤务按大队应到人数
    List<HashMap> selectorderlytweyd();

    //三级勤务按大队应到人数
    List<HashMap> selectorderlythreeyd();


}