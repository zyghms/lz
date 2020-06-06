package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * User:luanhuajuan
 * Date:2020-05-30 14:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GisLocation {
    private int id;   //地图坐标id
    private String name; //所属区域名称
    private String grade; //所属区域名称
    private String center;    //地图中心坐标
    private String  area; //地图的位置坐标
    private String marker; //默认空值  前端页面需要
    private String polygon; //默认空值 默认空值 前端页面需要
    private String color; //默认空值 前端页面需要
    private String remark; //备注   冗余字段
}
