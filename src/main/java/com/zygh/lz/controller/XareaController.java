package com.zygh.lz.controller;

import com.zygh.lz.admin.Xarea;
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
    public ResultBean selectXareabycondition(String battalion, String detachment, String station, String leader,String grid,String type){
        return xareaService.selectXareabycondition(battalion,detachment,station,leader,grid,type);
    }

    @PostMapping("insertXarea")
    public ResultBean insertXarea(Xarea xarea){
        return xareaService.insertXarea(xarea);
    }

    //修改
    @GetMapping("updateXarea")
    public ResultBean updateXarea(Xarea xarea){
        return xareaService.updateXarea(xarea);
    }

    //删除
    @GetMapping("deleteXarea")
    public ResultBean deleteXarea(Integer id){
        return xareaService.deleteXarea(id);
    }

    //根据名字模糊查询
    @GetMapping("selectXareaByName")
    public ResultBean selectXareaByName(String name){
        return xareaService.selectXareaByName(name);
    }

    //查询所有区域
    @GetMapping("selectXareaAll")
    public ResultBean selectXareaAll(){
        return xareaService.selectXareaAll();
    }

    //查询所有区域里民警应到、实到人数
    @GetMapping("selectonlineNumber")
    public ResultBean selectonlineNumber(Integer id){
        return xareaService.selectonlineNumber(id);
    }

    //按大队区分所有在线警力
    @GetMapping("selectpolice")
    public ResultBean selectpolice(){
        return xareaService.selectpolice();
    }

}
