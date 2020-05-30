package com.zygh.lz.service;

import com.zygh.lz.entity.Xdeclare;
import com.zygh.lz.vo.ResultBean;

public interface XdeclareService {

    //新增
    ResultBean insertXdeclare(Xdeclare xdeclare);

    //上报默认人
    ResultBean selectDefaultMan(Integer staffid);
}
