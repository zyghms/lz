package com.zygh.lz.controller;

import com.zygh.lz.admin.Urban;
import com.zygh.lz.service.urbanService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrbanController {
    @Autowired
    private urbanService urbanService;

    //查询所有区域
    @GetMapping("selectAllUrban")
    public ResultBean selectAllUrban() {
        return urbanService.selectAllUrban();
    }

    //新增区域
    @PostMapping("insertUrban")
    public ResultBean insertUrban(Urban urban) {
        return urbanService.insertUrban(urban);
    }

    //删除
    @GetMapping("deleteSomeUrban")
    public ResultBean deleteSomeUrban(Integer id) {
        return urbanService.deleteSomeUrban(id);
    }

    //修改
    @GetMapping("updateUrban")
    public ResultBean updateUrban(Urban urban) {
        return urbanService.updateUrban(urban);
    }

    //查询
    @GetMapping("seleteDimUrban")
    public ResultBean seleteDimUrban(Urban urban) {
        return urbanService.seleteDimUrban(urban);
    }

    //按照大队统计各管辖区域发现问题数目。统计
    @GetMapping("selectUrbanByCount")
    public ResultBean selectUrbanByCount(Integer sectionId){
        return urbanService.selectUrbanByCount(sectionId);
    }


}
