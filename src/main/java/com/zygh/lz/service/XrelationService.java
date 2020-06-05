package com.zygh.lz.service;

import com.zygh.lz.entity.Xrelation;
import com.zygh.lz.vo.ResultBean;

public interface XrelationService {
    //新增
    ResultBean insertXrelation(Xrelation xrelation);
    //修改
    ResultBean updateXrelation(Xrelation xrelation);
    //删除
    ResultBean  deleteXrelatonByid(Integer staffId,Integer xareaId);


}
