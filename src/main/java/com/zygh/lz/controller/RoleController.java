package com.zygh.lz.controller;

import com.zygh.lz.entity.Role;
import com.zygh.lz.service.RoleService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    //查询所有角色
    @GetMapping("selectAllRole")
    public ResultBean selectAllRole() {
        return roleService.selectAllRole();
    }

    //新增角色
    @PostMapping("addRole")
    public ResultBean addRole(Role role) {
        return roleService.addRole(role);
    }

    //修改
    @GetMapping("modifi")
    public ResultBean modifi(Role role) {
        return roleService.updateRoleById(role);
    }

    //删除
    @GetMapping("delRoleById")
    public ResultBean delRoleById(Integer id) {
        return roleService.delRoleById(id);
    }

}
