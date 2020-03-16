package com.zygh.lz.controller;

import com.zygh.lz.admin.Xarea;
import com.zygh.lz.service.staffService;
import com.zygh.lz.service.xareaService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Xarea")
@RestController
public class XareaController {
    @Autowired
    private xareaService xareaService;

    //根据大队，中队，岗位，中队领导
    @GetMapping("selectXareabycondition")
    public ResultBean selectXareabycondition(String battalion, String detachment, String station, String leader, String grid, String type) {
        return xareaService.selectXareabycondition(battalion, detachment, station, leader, grid, type);
    }

    @PostMapping("insertXarea")
    public ResultBean insertXarea(Xarea xarea) {
        return xareaService.insertXarea(xarea);
    }

    //修改
    @GetMapping("updateXarea")
    public ResultBean updateXarea(Xarea xarea) {
        return xareaService.updateXarea(xarea);
    }

    //删除
    @GetMapping("deleteXarea")
    public ResultBean deleteXarea(Integer id) {
        return xareaService.deleteXarea(id);
    }

    //根据名字模糊查询
    @GetMapping("selectXareaByName")
    public ResultBean selectXareaByName(String name) {
        return xareaService.selectXareaByName(name);
    }

    //查询所有区域
    @GetMapping("selectXareaAll")
    public ResultBean selectXareaAll() {
        return xareaService.selectXareaAll();
    }

    //查询所有区域里民警应到、实到人数
    @GetMapping("selectonlineNumber")
    public ResultBean selectonlineNumber(Integer id) {
        return xareaService.selectonlineNumber(id);
    }

    //按大队区分所有在线警力
    @GetMapping("selectpolice")
    public ResultBean selectpolice() {
        return xareaService.selectpolice();
    }

    //查询其他在线警力
    @GetMapping("selectqt")
    public ResultBean selectqt(String conment) {
        return xareaService.selectqt(conment);
    }

    //日间固定岗/高峰岗
    @GetMapping("selectfixationRJ")
    public ResultBean selectfixationRJ(String station) {
        return xareaService.selectfixationRJ(station);
    }

    //日间重点机关岗
    @GetMapping("selectemphasisRJ")
    public ResultBean selectemphasisRJ() {
        return xareaService.selectemphasisRJ();
    }

    //日间铁骑
    @GetMapping("selectcavalryRJ")
    public ResultBean selectcavalryRJ() {
        return xareaService.selectcavalryRJ();
    }

    //日间网格警力部署
    @GetMapping("selectgriddingRJ")
    public ResultBean selectgriddingRJ() {
        return xareaService.selectgriddingRJ();
    }

    //日间快速岗部署
    @GetMapping("selectexpresswayRJ")
    public ResultBean selectexpresswayRJ(String station) {
        return xareaService.selectexpresswayRJ(station);
    }

    //日间其他部署
    @GetMapping("selectqtRJ")
    public ResultBean selectqtRJ() {
        return xareaService.selectqtRJ();
    }

    //全部在线人
    @GetMapping("selectInformant")
    public ResultBean selectInformant() {
        return xareaService.selectInformant();
    }
    //夜间夜巡
    @GetMapping("selectNightTour")
    public ResultBean selectNightTour() {
        return xareaService.selectNightTour();
    }

    //夜间快速/高速警力部署
    @GetMapping("selectcelerity")
    public ResultBean selectcelerity(String station) {
        return xareaService.selectcelerity(station);
    }
    //夜间其他警力部署
    @GetMapping("selectqita")
    public ResultBean selectqita() {
        return xareaService.selectqita();
    }

    //九主六块十六示范区
    @GetMapping("selectDemonstrationPlot")
    public ResultBean selectDemonstrationPlot(String station) {
        return xareaService.selectDemonstrationPlot(station);
    }

    //根据区域名字模糊匹配部署警力
    @GetMapping("selctStrength")
    public ResultBean selctStrength(String name) {
        return xareaService.selctStrength(name);
    }

    //九主六块警力详情
    @GetMapping("selectAllByDemonstration")
    public ResultBean selectAllByDemonstration(String station) {
        return xareaService.selectAllByDemonstration(station);
    }




}
