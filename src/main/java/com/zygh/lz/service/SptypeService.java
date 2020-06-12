package com.zygh.lz.service;

import com.zygh.lz.entity.Sptype;
import com.zygh.lz.vo.ResultBean;

<<<<<<< HEAD
=======
import java.util.HashMap;
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
import java.util.List;

public interface SptypeService {
    //新增任务组
    ResultBean addSptype(Sptype sptype);

    //修改任务组
    ResultBean updateSptype(Sptype sptype);

    //根据id任务组类型
    ResultBean delSptype(Integer id);
    //根据id任务组类型
    List<Sptype> selectSptypeInfo(Integer type);

}
