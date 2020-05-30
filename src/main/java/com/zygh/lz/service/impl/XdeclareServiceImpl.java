package com.zygh.lz.service.impl;

import com.zygh.lz.entity.Staff;
import com.zygh.lz.entity.Xdeclare;
import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.dao.StaffMapper;
import com.zygh.lz.dao.XdeclareMapper;
import com.zygh.lz.service.XdeclareService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class XdeclareServiceImpl implements XdeclareService {
    @Autowired
    private XdeclareMapper xdeclareMapper;
    @Autowired
    private StaffMapper staffMapper;

    /**
     * 新增申报
     * @param xdeclare
     * @return
     */
    @Override
    public ResultBean insertXdeclare(Xdeclare xdeclare) {
        xdeclare.setEstablishtime(new Date());
        return ResultUtil.execOp(xdeclareMapper.insertSelective(xdeclare),"新增");
    }
    @Override
    public ResultBean selectDefaultMan(Integer staffid) {
        //查询当前登录人信息
        Staff staff = staffMapper.selectByPrimaryKey(staffid);
        //查询当前登录人直系领导
        Staff staff1 = staffMapper.selectStaffBypid(staff.getStafffPid());
        if(staff1!=null){
            return ResultUtil.setOK("success",staff1);
        }
        return ResultUtil.setError(SystemCon.RERROR1,"error","没有直系领导，请联系管理员！");
    }
}
