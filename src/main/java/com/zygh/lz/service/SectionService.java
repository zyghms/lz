package com.zygh.lz.service;

import com.zygh.lz.entity.Section;
import com.zygh.lz.vo.ResultBean;

public interface SectionService {
    //新增部门
    ResultBean addSection(Section section);
    //修改部门
    ResultBean modefiSectionById(Section section);
    //根据id删除部门
    ResultBean delSectionById(Integer id);
    //根据性别,路长，部门查询
    ResultBean selectAllBySection(String sex,String staffPost,String staffName,String staffPid,String sectionName,String stafftype);
    //按层级推送
    ResultBean selectAllSection();
    //查询所有
    ResultBean selectAllSectionInfo();
    //部门人员信息查询那里的模糊查询
    ResultBean selectSectionByCt(String sex,String staffHierarchy,String sectionName);
    //根据部门id查询所有部门人员
    ResultBean selectBySectionId(Integer sectionId,String staffHierarchy);
    //根据大队id查询大队下面的中队
    ResultBean selectDetachmentByid(Integer id);
    //勤务部门层级列表
    ResultBean findSectionByTier();
}
