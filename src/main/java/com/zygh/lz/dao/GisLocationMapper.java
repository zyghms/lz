package com.zygh.lz.dao;
import com.zygh.lz.entity.GisLocation;
import com.zygh.lz.vo.ResultBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GisLocationMapper {
    List<GisLocation> selectGisLocation();

}