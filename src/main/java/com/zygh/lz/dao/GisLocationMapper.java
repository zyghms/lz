package com.zygh.lz.dao;
import com.zygh.lz.vo.ResultBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GisLocationMapper {
    ResultBean selectGisLocation();

}