package com.zygh.lz.util;

import java.util.Map;

/**
 * Description:判断
 * User:luanhuajuan
 * Date:2020-05-28 10:45
 */
public class GraphUtils {

    public static void main(String[] args) {
        Map[] map=new Map[]{};
      //  113.726268,34.734365,113.726787,34.76125,113.711616,34.773958,113.709723,34.776454,
        // 113.708655,34.778845,113.706652,34.79298,113.705708,34.798997,113.705,34.801438,
        // 113.69264,34.835467,113.692051,34.846322,113.708187,34.845847,113.717436,34.844676,
        // 113.722984,34.843436,113.73243,34.840168,113.739923,34.836936,113.756032,34.830562,
        // 113.766694,34.82746,113.774644,34.826165,113.780749,34.825751,113.833362,34.825598,

        // 113.851016,34.825356,113.871307,34.823329,113.880274,34.823377,113.923078,34.827795,

        // 113.932358,34.828055,113.923111,34.798832,113.920657,34.728331,113.92019,34.725112,

        // 113.920281,34.724799,113.920061,34.724138,113.917376,34.718365,113.91285,34.721835,
        // 113.909927,34.723757,113.906499,34.725459,113.903462,34.726543,113.899016,34.727716,
        // 113.890443,34.728836,113.880938,34.729987,113.876855,34.730802,113.862005,34.733033
        // 113.849035,34.734867,113.843548,34.734863,113.841434,34.734766,113.83803,34.735065,
        // 113.832529,34.735537,113.821038,34.735956,113.817959,34.736207,113.811691,34.736045,
        // 113.807078,34.736186,113.796757,34.735683,113.773867,34.735331,113.726268,34.734365'
        Point[] ps = new Point[] {
                new Point(113.726268,34.734365),
                new Point(113.726787,34.76125),//114.295688,30.592879
                new Point(113.711616,34.773958),
                new Point(113.709723,34.776454),
                new Point(113.708655,34.778845),
                new Point(113.706652,34.79298),
                new Point(113.705708,34.798997),
                new Point(113.705,34.801438),
                new Point(113.69264,34.835467),
                new Point(113.692051,34.846322),
                new Point(113.708187,34.845847),
                new Point(113.717436,34.844676),
                new Point(113.722984,34.843436),
                new Point( 113.722984,34.843436),
                new Point( 113.73243,34.840168),
                new Point( 113.739923,34.836936),
                new Point( 113.756032,34.830562),
                new Point( 113.766694,34.82746),
                new Point( 113.774644,34.826165),
                new Point( 113.780749,34.825751),
                new Point( 113.833362,34.825598),
                new Point( 113.851016,34.825356),
                new Point( 113.871307,34.823329),
                new Point( 113.880274,34.823377),
                new Point( 113.923078,34.827795),
                new Point( 113.932358,34.828055),
                new Point( 113.923111,34.798832),
                new Point( 113.920657,34.728331),
                new Point( 113.92019,34.725112),
                new Point( 113.920281,34.724799),
                new Point( 113.920061,34.724138),
                new Point( 113.917376,34.718365),
                new Point( 113.91285,34.721835),
                new Point( 113.909927,34.723757),
                new Point( 113.906499,34.725459),
                new Point( 113.903462,34.726543),
                new Point( 113.899016,34.727716),
                new Point( 113.890443,34.728836),
                new Point( 113.880938,34.729987),
                new Point( 113.876855,34.730802),
                new Point( 113.862005,34.733033),
                new Point( 113.849035,34.734867),
                new Point( 113.843548,34.734863),
                new Point( 113.841434,34.734766),
                new Point( 113.83803,34.735065),
                new Point( 113.832529,34.735537),
                new Point( 113.821038,34.735956),
                new Point( 113.817959,34.736207),
                new Point( 113.811691,34.736045),
                new Point( 113.807078,34.736186),
                new Point( 113.796757,34.735683),
                new Point( 113.773867,34.735331),
                new Point( 113.726268,34.734365)};
        Point n1=new Point(113.73390197753908,34.80691909790039);
        Point n2 = new Point(113.76823425292969,34.76297378540039);
        Point n3 = new Point(113.86642456054688,34.76743698120117);
        Point y1 = new Point(113.86470794677734,30.587987);
        Point y2 = new Point(120.1866 , 30.2672);
        Point y4 = new Point(120.1869 , 30.2718);
        System.out.println( "n1:" + isPtInPoly(n1.getX() , n1.getY() , ps));
        System.out.println( "n2:" + isPtInPoly(n2.getX() , n2.getY() , ps));
        System.out.println( "n3:" + isPtInPoly(n3.getX() , n3.getY() , ps));
        System.out.println( "y1:" + isPtInPoly(y1.getX() , y1.getY() , ps));
        System.out.println( "y2:" + isPtInPoly(y2.getX() , y2.getY() , ps));
        System.out.println( "y4:" + isPtInPoly(y4.getX() , y4.getY() , ps));
    }
    public static boolean isPtInPoly (double ALon , double ALat , Point[] ps) {
        int iSum, iCount, iIndex;
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;
        if (ps.length < 3) {
            return false;
        }
        iSum = 0;
        iCount = ps.length;
        for (iIndex = 0; iIndex<iCount;iIndex++) {
            if (iIndex == iCount - 1) {
                dLon1 = ps[iIndex].getX();
                dLat1 = ps[iIndex].getY();
                dLon2 = ps[0].getX();
                dLat2 = ps[0].getY();
            } else {
                dLon1 = ps[iIndex].getX();
                dLat1 = ps[iIndex].getY();
                dLon2 = ps[iIndex + 1].getX();
                dLat2 = ps[iIndex + 1].getY();
            }
            // 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
            if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {
                if (Math.abs(dLat1 - dLat2) > 0) {
                    //得到 A点向左射线与边的交点的x坐标：
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat) ) / (dLat1 - dLat2);
                    // 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：
                    if (dLon < ALon) {
                        iSum++;
                    }
                }
            }
        }
        if ((iSum % 2) != 0) {
            return true;
        }
        return false;
    }
}
