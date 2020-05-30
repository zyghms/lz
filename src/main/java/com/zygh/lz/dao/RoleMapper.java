package com.zygh.lz.dao;

import com.zygh.lz.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer sysRoleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer sysRoleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAllRole();

}