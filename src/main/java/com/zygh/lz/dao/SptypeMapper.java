package com.zygh.lz.dao;

import com.zygh.lz.entity.Sptype;

import java.util.List;

/**
 * Description:
 * User:luanhuajuan
 * Date:2020-06-02 17:40
 */
public interface SptypeMapper {
    //查询所有类型
    List<Sptype> selectAllType();
}
