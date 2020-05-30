package com.zygh.lz.service.impl;

import com.zygh.lz.entity.Menu;
import com.zygh.lz.entity.Role;
import com.zygh.lz.entity.Subsystem;
import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.dao.MenuMapper;
import com.zygh.lz.dao.RoleMapper;
import com.zygh.lz.dao.SubsystemMapper;
import com.zygh.lz.service.RoleService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SubsystemMapper subsystemMapper;
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public ResultBean selectAllRole() {
        List<Role> role = roleMapper.selectAllRole();
        for (int i = 0; i < role.size(); i++) {
            String subsysname = "";
            String menuname = "";
            String controlname = "";
            String subsystemid = role.get(i).getSubsystemUsetype();
            String menuid = role.get(i).getMenuUsetype();
            String controlid = role.get(i).getControlUsetype();
            if (subsystemid != null) {
                String[] subs = subsystemid.split(",");
                for (int j = 0; j < subs.length; j++) {
                    int subsysid = Integer.valueOf(subs[j]);
                    Subsystem subsystem = subsystemMapper.selectByPrimaryKey(subsysid);
                    subsysname = subsysname + subsystem.getSubsystemName() + " ";
                }
            }
            if (menuid != null) {
                String[] menus = menuid.split(",");
                for (int k = 0; k < menus.length; k++) {
                    int menuids = Integer.valueOf(menus[k]);
                    Menu menu = menuMapper.selectByPrimaryKey(menuids);
                    menuname = menuname + menu.getMenuName() + " ";
                }
            }
            /*if (controlid != null) {
                String[] controls = controlid.split(",");
                for (int l = 0; l < controls.length; l++) {
                    int subsysid = Integer.valueOf(controls[l]);
                    Control controlids = controlService.selectByPrimaryKey(subsysid);
                    controlname = controlname + controlids.getControlName() + " ";
                }
            }*/


            role.get(i).setSubsystemUsetypeName(subsysname);
            role.get(i).setMenuUsetypeName(menuname);
            role.get(i).setControlUsetypeName(controlname);
        }
        LinkedHashMap<String, Object> json = new LinkedHashMap<>();
        json.put("content", role);
       if(json.size() > 0){
           return ResultUtil.setOK("success",json);
       }
       return ResultUtil.setError(SystemCon.RERROR1,"error",null);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    public ResultBean addRole(Role role) {
        return ResultUtil.execOp(roleMapper.insertSelective(role),"添加");
    }

    /**
     * 修改
     * @param role
     * @return
     */
    @Override
    public ResultBean updateRoleById(Role role) {
        return ResultUtil.execOp(roleMapper.updateByPrimaryKeySelective(role),"修改");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ResultBean delRoleById(Integer id) {
        return ResultUtil.execOp(roleMapper.deleteByPrimaryKey(id),"删除");
    }
}
