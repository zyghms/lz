package com.zygh.lz.service;

import com.zygh.lz.admin.Xarea;
import com.zygh.lz.vo.ResultBean;

import java.util.HashMap;
import java.util.List;

public interface xareaService {
    //根据大队，中队，岗位，中队领导
    ResultBean selectXareabycondition(String battalion, String detachment, String station, String leader, String grid, String type);

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

    //夜间快速岗
    ResultBean selectks();

    //查询其他
    ResultBean selectqt(String conment);


    /**
     * 日间警力部署
     */
    //日间固定岗部署人员按大队细分
    ResultBean selectfixationRJ(String station);

    //日间重点单位岗
    ResultBean selectemphasisRJ();

    //日间铁骑
    ResultBean selectcavalryRJ();

    //网格警组
    ResultBean selectgriddingRJ();

    //日间高速/快速
    ResultBean selectexpresswayRJ(String station);

    //日间其他警力部署
    ResultBean selectqtRJ();

    //在线人详情
    ResultBean selectInformant();

    //夜间夜巡警力部署
    ResultBean selectNightTour();

    //夜间快速/高速警力部署
    ResultBean selectcelerity(String station);

    //夜间其他
    ResultBean selectqita();

    //九主六块十六示范区
    ResultBean selectDemonstrationPlot(String station);

    //根据区域名字模糊匹配部署警力
    ResultBean selctStrength(String name);

    //九主六块多有人
    ResultBean selectAllByDemonstration(String station);
}
