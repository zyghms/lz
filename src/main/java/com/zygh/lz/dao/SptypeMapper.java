package com.zygh.lz.dao;

import com.zygh.lz.entity.Sptype;
import com.zygh.lz.vo.ResultBean;

import java.util.List;

/**
 * Description:
 * User:luanhuajuan
 * Date:2020-06-02 17:40
 */
public interface SptypeMapper {
    //查询所有类型
    List<Sptype> selectAllType();

    //新增任务组
    int  insertSelective(Sptype sptype);

    //修改任务组
    int updateByPrimaryKey(Sptype sptype);

    //根据id任务组类型
    int  delSptype(Integer id);

    List<Sptype> selectSptypeInfo(Integer type);
}
