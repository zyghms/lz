package com.zygh.lz.controller;

import com.zygh.lz.entity.Asset;
import com.zygh.lz.service.AssetService;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AssetController {
    @Autowired
    private AssetService assetService;

    //资产新增
    @PostMapping("addAsset")
    public ResultBean addAsset(Asset asset){
        return assetService.addAsset(asset);
    }
    //资产修改
    @GetMapping("updateAsset")
    public ResultBean updateAsset(Asset asset){
        return assetService.updateAsset(asset);
    }
    //资产删除
    @GetMapping("delAsset")
    public ResultBean delAsset(Integer id){
        return assetService.delAsset(id);
    }
    //按照资产类型、所属部门查询
    @GetMapping("selectAssetBytype")
    public ResultBean selectAssetBytype(String assetName,String type,String dept){
        return assetService.selectAssetBytype(assetName,type,dept);
    }

    //查询所有资产类型
    @GetMapping("selectByTypeAsset")
    public ResultBean selectByTypeAsset(HttpServletRequest request){
        request.setAttribute("result",assetService.selectByTypeAsset());
        return assetService.selectByTypeAsset();
    }
}
