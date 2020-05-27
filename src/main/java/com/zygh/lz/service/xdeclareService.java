package com.zygh.lz.service;

import com.zygh.lz.admin.Xdeclare;
import com.zygh.lz.vo.ResultBean;

public interface xdeclareService {

    //新增
    ResultBean insertXdeclare(Xdeclare xdeclare);

    //上报默认人
    ResultBean selectDefaultMan(Integer staffid);
}
