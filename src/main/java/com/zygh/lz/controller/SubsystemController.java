package com.zygh.lz.controller;

import com.zygh.lz.service.subsystemService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubsystemController {
    @Autowired
    private subsystemService subsystemService;

    //查询所有子系统
    @GetMapping("selectAllSubsystem")
    public ResultBean selectAllSubsystem(){
        return subsystemService.selectAllSubsystem();
    }

    @GetMapping("selectControlsystem")
    public ResultBean selectControlsystem(){
        return subsystemService.selectControlsystem();
    }
    @GetMapping("selectControlsubsystem")
    public ResultBean selectControlsubsystem(Integer id){
        return subsystemService.selectControlsubsystem(id);
    }
    @GetMapping("selectRank")
    public ResultBean selectRank(){
        return subsystemService.selectRank();
    }
    @GetMapping("selectRepair")
    public ResultBean selectRepair(){
        return subsystemService.selectRepair();
    }
}
