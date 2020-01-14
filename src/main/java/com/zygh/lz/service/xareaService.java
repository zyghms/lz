package com.zygh.lz.service;

import com.zygh.lz.admin.Xarea;
import com.zygh.lz.vo.ResultBean;

import java.util.List;

public interface xareaService {
    //根据大队，中队，岗位，中队领导
    ResultBean selectXareabycondition(String battalion,String detachment, String station,String leader,String grid,String type);
    //新增
    ResultBean insertXarea(Xarea xarea);
    //修改
    ResultBean updateXarea(Xarea xarea);
    //删除
    ResultBean deleteXarea(Integer id);
    //根据名字模糊查询
    ResultBean selectXareaByName(String Name);
    //查询所有区域
    ResultBean selectXareaAll();
    //根据id查询区域信息
    ResultBean selectonlineNumber(Integer id);


    ResultBean selectpolice();

}
