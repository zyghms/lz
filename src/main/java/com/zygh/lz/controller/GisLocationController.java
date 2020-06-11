package com.zygh.lz.controller;

import com.zygh.lz.entity.GisLocation;
import com.zygh.lz.service.GisLocationService;
import com.zygh.lz.util.Point;
import com.zygh.lz.vo.ResultBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User:luanhuajuan
 * Date:2020-05-29 9:11
 */
@RestController
public class GisLocationController {

    @Resource
    private GisLocationService gisLocationService;

    /**
     * Description:查询地图坐标信息
     * User:luanhuajuan
     * Date:2020-05-29 9:11
     */
    @GetMapping(value = "selectGisLocation")
    public ResultBean selectGisLocation(){
        return gisLocationService.selectGisLocation();

    }

    @GetMapping(value="getGisLocation")
    public ResultBean getGisLocation(String str ){
         String strs[]=str.split(",");
         List <Point> pointList=new ArrayList<Point>();
         for (int i=0;i<strs.length;i++){
             if(i%2 == 0){
                 pointList.get(i).setX(Double.parseDouble(strs[i]));
             }
             if(i %2 !=0){
                 pointList.get(i).setY(Double.parseDouble(strs[i]));
             }
        }
        ResultBean  gisLocationList= gisLocationService.selectGisLocation();
        return null;

    }


//    @GetMapping(value="getGisLocation")
//    public ResultBean getGisLocation(String str){
//      List<>  gisLocationService.selectGisLocation()
//        //1.查询数据库gislocation表的数据
//        //2.根据工具类判断传过来的点是否在某个大队的区域内
//        //3.将数据用json的方式返回
//
//        return  gisLocationService.getGisLocation(str);
//
//    }
//    GraphUtils graphUtils=new GraphUtils();
//    GraphUtils.isPtInPoly(n1.getX() , n1.getY() , ps));



}
