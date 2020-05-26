package com.zygh.lz.controller;

import com.zygh.lz.admin.Xarea;
import com.zygh.lz.service.staffService;
import com.zygh.lz.service.xareaService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class buttJointController {
    @Autowired
    private xareaService xareaServce;
    @Autowired
    private staffService staffService;

    /**
     * 根据点线面查询区域
     * @param xarea
     * @return
     */
    @GetMapping("selectXareaByInfo")
    public ResultBean selectXareaByInfo(Xarea xarea) {
        return xareaServce.selectXareaByInfo(xarea);
    }

    /**
     *根据区域查询相应警力
     * @param xarea
     * @return
     */
    @GetMapping("selectPoliceNumber")
    public ResultBean selectPoliceNumber(Xarea xarea) {
        return xareaServce.selectPoliceNumber(xarea);
    }

    //根据区域查询相应警力
    @GetMapping("selctStrengthById")
    public ResultBean selctStrengthById(Xarea xarea) {
        return xareaServce.selctStrengthById(xarea);
    }

    //人员信息列表
    @GetMapping("selectStaffByInfo")
    public ResultBean selectStaffByInfo() {
        return staffService.selectStaffByInfo();
    }

    //全部在线人
    @GetMapping("selectInformantAll")
    public ResultBean selectInformantAll() {
        return xareaServce.selectInformant();
    }

    //任务组信息
    @GetMapping("selectTaskSetInfo")
    public ResultBean selectTaskSetInfo(){return xareaServce.selectTaskSetInfo();}
    //任务信息
    @GetMapping("selectTaskInfo")
    public ResultBean selectTaskInfo(){
        return xareaServce.selectTaskInfo();}


}
