package com.zygh.lz.dao;

import com.zygh.lz.entity.Gps;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GpsMapper {
    int deleteByPrimaryKey(Integer sysGpsId);

    int insert(Gps record);

    int insertSelective(Gps record);

    Gps selectByPrimaryKey(Integer sysGpsId);

    int updateByPrimaryKeySelective(Gps record);

    int updateByPrimaryKey(Gps record);

    List<Gps> selectGpsByRecord(@Param("sys_staff_id") int sys_staff_id,
                                @Param("sysPatrolRecordId") int sysPatrolRecordId);

    List<Gps> selectAllGps() ;

    //最新GPS定位点
    Gps gpsEnd(Integer id);
}