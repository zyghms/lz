package com.zygh.lz.controller;

import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.dao.AdmameraMapper;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zygh.lz.entity.AdmameraWithBLOBs;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AdmameraController {

    @Autowired
    private AdmameraMapper admameraMapper;

    @GetMapping("selectAllByToday")
    public ResultBean selectAllByToday(){
        List<AdmameraWithBLOBs> admameraWithBLOBs = admameraMapper.selectAllByToday();
        if(admameraWithBLOBs.size()>0||admameraWithBLOBs.size()==0){
            return ResultUtil.setOK("success",admameraWithBLOBs);
        }
        return ResultUtil.setError(SystemCon.RERROR1,"error",null);

    }
}
