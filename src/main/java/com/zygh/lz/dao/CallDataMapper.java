package com.zygh.lz.dao;

import com.zygh.lz.entity.CallData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value ="callDataMapper" )
@Mapper
public interface CallDataMapper {
    int insert(CallData calldata);
    List<CallData> selectInfo(@Param("names") String names);


}