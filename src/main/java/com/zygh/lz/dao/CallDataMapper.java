package com.zygh.lz.dao;

import com.zygh.lz.entity.CallData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component(value ="callDataMapper" )
@Mapper
public interface CallDataMapper {
    int insert(CallData calldata);


}