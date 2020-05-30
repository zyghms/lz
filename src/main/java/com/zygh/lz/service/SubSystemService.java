package com.zygh.lz.service;

import com.zygh.lz.vo.ResultBean;

public interface SubSystemService {
    //查询所有子系统
    ResultBean selectAllSubsystem();


    ResultBean selectControlsystem();
    ResultBean selectControlsubsystem(Integer id);
    ResultBean selectRank();
    ResultBean selectRepair();

}
