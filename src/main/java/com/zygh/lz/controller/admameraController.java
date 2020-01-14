package com.zygh.lz.controller;

import com.zygh.lz.admin.admameraWithBLOBs;
import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.mapper.admameraMapper;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class admameraController {

    @Autowired
    private admameraMapper admameraMapper;

    @GetMapping("selectAllByToday")
    public ResultBean selectAllByToday(){
        List<admameraWithBLOBs> admameraWithBLOBs = admameraMapper.selectAllByToday();
        if(admameraWithBLOBs.size()>0||admameraWithBLOBs.size()==0){
            return ResultUtil.setOK("success",admameraWithBLOBs);
        }
        return ResultUtil.setError(SystemCon.RERROR1,"error",null);
    }
}
