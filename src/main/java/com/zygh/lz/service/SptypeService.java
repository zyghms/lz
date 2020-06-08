package com.zygh.lz.service;

import com.zygh.lz.entity.Section;
import com.zygh.lz.entity.Sptype;
import com.zygh.lz.vo.ResultBean;

import java.util.HashMap;
import java.util.List;

public interface SptypeService {
    //新增任务组
    ResultBean addSptype(Sptype sptype);

    //修改任务组
    ResultBean updateSptype(Sptype sptype);

    //根据id任务组类型
    ResultBean delSptype(Integer id);
    //根据id任务组类型
    List<Sptype> selectSptypeInfo(String battalion);

}
