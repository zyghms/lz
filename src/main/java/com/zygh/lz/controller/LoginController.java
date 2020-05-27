package com.zygh.lz.controller;

import com.zygh.lz.admin.Register;
import com.zygh.lz.admin.Staff;
import com.zygh.lz.service.staffService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private staffService staffService;

    //登录
    @PostMapping("staffLogin")
    public ResultBean staffLogin(String staff_tel, String staff_password){
        return staffService.selectByLogin(staff_tel,staff_password);
    }

    //注册
    @PostMapping("register")
    public ResultBean register(Staff staff){
        return staffService.register(staff);
    }
    //校验
    @PostMapping("usercheck")
    public ResultBean usercheck(String name){
        return staffService.usercheck(name);
    }
    //修改
    @GetMapping("modifu")
    public ResultBean modifu(Staff staff){
        return staffService.updaStaffInfoById(staff);
    }

    //修改
    @GetMapping("modifuByPass")
    public ResultBean modifuByPass(Staff staff){
        return staffService.modifuByPass(staff);
    }

    //删除
    @GetMapping("delUserBySysId")
    public ResultBean delUserBySysId(Integer id){
        return staffService.delStaffInfoById(id);
    }
    //登录
    @PostMapping("appLogin")
    public ResultBean appLogin(String user, String password,String IMEI,String sf){
        return staffService.appLogin(user,password,IMEI,sf);
    }

    //登出
    @PostMapping("appLoginOut")
    public ResultBean appLoginOut(String IMEI,String user){
        return staffService.appLoginOut( IMEI, user);
    }

    //是否重新登录（顶掉）
    @PostMapping("updateRegiser")
    public ResultBean updateRegiser(Register register){
        return staffService.updateRegiser(register);
    }

}
