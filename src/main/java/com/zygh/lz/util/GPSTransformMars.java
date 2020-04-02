package com.zygh.lz.util;

import com.zygh.lz.mapper.XareaMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class GPSTransformMars {
    /*
     * GPS坐标转换为高德地图坐标
     * 输入GPS坐标，单位度，数据类型double，参数一为Lat,参数二为Lng
     * 输出高德地图坐标，单位度，数据类型double[]，参数一为Lat,参数二为Lng
     *
     * */
    double x_PI = 3.14159265358979324 * 3000.0 / 180.0;
    double PI = 3.1415926535897932384626;
    double a = 6378245.0;
    double ee = 0.00669342162296594323;


    @Autowired
    private XareaMapper xareaMapper;

    /**
     * WGS84转GCj02
     *
     * @param lng
     * @param lat
     * @returns {*[]}
     */
    public double[] transLatLng(double lng, double lat) {
        double[] point = new double[2];
        double dlat = transformlat(lng - 105.0, lat - 35.0);
        double dlng = transformlng(lng - 105.0, lat - 35.0);
        double radlat = lat / 180.0 * PI;
        double magic = Math.sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = Math.sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
        dlng = (dlng * 180.0) / (a / sqrtmagic * Math.cos(radlat) * PI);
        point[0] = lng + dlng;
        point[1] = lat + dlat;
        return point;
    }

    private double transformlat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * PI) + 40.0 * Math.sin(lat / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * PI) + 320 * Math.sin(lat * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private double transformlng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * PI) + 40.0 * Math.sin(lng / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * PI) + 300.0 * Math.sin(lng / 30.0 * PI)) * 2.0 / 3.0;
        return ret;
    }

    //坐标转换，
    public static String GCj2TOWGS(String gps) {
        String[] split = gps.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
        String loastr = "";
        String loc = "";
        for (int k = 0; k < split.length; k++) {
            if (k % 2 == 0) {
                double lat = Double.valueOf(split[k + 1]); //标记纬度
                double lng = Double.valueOf(split[k]);     //标记经度
                //double[] doubles = GCJ2WGSUtils.gcj02towgs84(lng, lat);
                double[] doubles = GCJ2WGSUtils.wgs84togcj02(lng, lat);
                loastr = String.valueOf(doubles[0]) + "," + String.valueOf(doubles[1]) + ",";
                loc += loastr;
            }
        }

        return loc.substring(0, loc.length() - 1);
    }

    public static void main(String[] args) {
        String gps="113.58042597770692,34.46205139160156";
        //String gps="113.6996570974796,34.768613208461346";
        String[] split = gps.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
        String loastr = "";
        String loc = "";
        for (int k = 0; k < split.length; k++) {
            if (k % 2 == 0) {
                double lat = Double.valueOf(split[k + 1]); //标记纬度
                double lng = Double.valueOf(split[k]);     //标记经度
                //double[] doubles = GCJ2WGSUtils.gcj02towgs84(lng, lat);
                double[] doubles = GCJ2WGSUtils.wgs84togcj02(lng, lat);
                loastr = String.valueOf(doubles[0]) + "," + String.valueOf(doubles[1])+",";
                loc += loastr;
            }
        }

        System.out.println(loc.substring(0,loc.length()-1));
    }


}
