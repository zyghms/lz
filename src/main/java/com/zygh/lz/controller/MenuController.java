package com.zygh.lz.controller;

import com.zygh.lz.entity.Menu;
import com.zygh.lz.service.MenuService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    //根据角色、根据子系统id推送菜单的函数
    @GetMapping("subMenu")
    public ResultBean subMenu(Integer sys_role_id, Integer sys_subsystem_id) {
        return menuService.subMenu(sys_role_id,sys_subsystem_id);
    }

    //查询所有的子系统以及所包含的菜单
    @GetMapping("selectAllSubAndMenu")
    public ResultBean selectAllSubAndMenu(){
        return menuService.selectAllSubAndMenu();
    }

    // 查询多有菜单和子系统
    @GetMapping("selectAllByMenu")
    public ResultBean selectAllByMenu(){
        return menuService.selectAllByMenu();
    }

    //添加菜单
    @PostMapping("insertIntoMenu")
    public ResultBean insertIntoMenu(Menu menu){
        return menuService.insertIntoMenu(menu);
    }
    //修改菜单
    @GetMapping("updateInfoById")
    public ResultBean dateMenu(Menu menu){
        return menuService.dateMenu(menu);
    }
    //删除菜单
    @GetMapping("delMenu")
    public ResultBean delMenu(Integer id){
        return menuService.delMenu(id);
    }
    //模糊查询
    @GetMapping("selectByName")
    public ResultBean selectByName(String name,Integer id){
        return menuService.selectByName(name,id);
    }
}
