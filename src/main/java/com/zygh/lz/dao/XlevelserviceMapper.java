package com.zygh.lz.dao;
import com.zygh.lz.entity.Xlevelservice;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

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
    Integer selectorderlyAll(String sectionName);

    //一级勤务按大队应到人数
    List<HashMap> selectorderlyoneyd(String sectionName);
    Integer selectyjyd(String sectionName);
    //二级勤务按大队应到人数
    List<HashMap> selectorderlytweyd(String sectionName);
    Integer selectejyd(String sectionName);
    //三级勤务按大队应到人数
    List<HashMap> selectorderlythreeyd(String sectionName);
    Integer selectsjyd(String sectionName);

    //按等级大队查询区域
    List<Xlevelservice> selectxleveBydj(@Param("hierarchy") Integer hierarchy,
                                        @Param("sectionName") String sectionName);

    List<Xlevelservice> selectXleveAll();


    //按等级按大队展示大队
    List<HashMap> selectXlevedJ(Integer hierarchy);

    //按等级按大队展示详情
    List<Xlevelservice> selectXleverdJnum(@Param("hierarchy") Integer hierarchy,
                                          @Param("sectionName") String sectionName);


}