package com.zygh.lz.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * User:luanhuajuan
 * Date:2020-05-28 11:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    private Double x;
    private Double y;
    //没有删除下面方法的原因是因为害怕lombok出现问题
//    public Point (Double x , Double y) {
//        this.x = x;
//        this.y = y;
//    }
//    public Double getX() {
//        return x;
//    }
//    public void setX(Double x) {
//        this.x = x;
//    }
//    public Double getY() {
//        return y;
//    }
//    public void setY(Double y) {
//        this.y = y;
//    }
}
