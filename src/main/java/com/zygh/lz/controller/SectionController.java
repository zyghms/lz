package com.zygh.lz.controller;

import com.zygh.lz.admin.Section;
import com.zygh.lz.service.sectionService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SectionController {
    @Autowired
    private sectionService sectionService;

    //新增部门
    @PostMapping("addSection")
    public ResultBean addSection(Section section) {
        return sectionService.addSection(section);
    }

    //修改部门
    @GetMapping("modefiSectionById")
    public ResultBean modefiSectionById(Section section) {
        return sectionService.modefiSectionById(section);
    }

    //根据id删除部门
    @GetMapping("delSectionById")
    public ResultBean delSectionById(Integer id) {
        return sectionService.delSectionById(id);
    }

    //根据姓名，性别，职位，部门，领导模糊查询所有部门人员(查询人员的模糊查询)
    @GetMapping("selectAllBySection")
    public ResultBean selectAllBySection(String sex,String staffPost,String staffName,String staffPid,String sectionName) {
        return sectionService.selectAllBySection(sex,staffPost,staffName,staffPid,sectionName);
    }

    //按层级推送
    @GetMapping("selectAllSection")
    public ResultBean selectAllSection(){
        return sectionService.selectAllSection();
    }

    //查询所有部门
    @GetMapping("selectSection")
    public ResultBean selectSection() {
        return sectionService.selectAllSectionInfo();
    }

    //门人员信息查询那里的模糊查询
    @GetMapping("selectSectionByCt")
    public ResultBean selectSectionByCt(String sex, String staffHierarchy, String sectionName){
        return sectionService.selectSectionByCt(sex,staffHierarchy,sectionName);}
    //根据部门id查询所有部门人员
    @GetMapping("selectBySectionId")
    public ResultBean selectBySectionId(Integer sectionId,String staffHierarchy){
        return sectionService.selectBySectionId(sectionId,staffHierarchy);
    }
}
