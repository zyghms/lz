package com.zygh.lz.service.impl;

import com.zygh.lz.admin.Gps;
import com.zygh.lz.admin.Patrolrecord;
import com.zygh.lz.admin.Staff;
import com.zygh.lz.admin.Xarea;
import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.mapper.GpsMapper;
import com.zygh.lz.mapper.PatrolrecordMapper;
import com.zygh.lz.mapper.StaffMapper;
import com.zygh.lz.mapper.XareaMapper;
import com.zygh.lz.service.xareaService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XareaServiceImpl implements xareaService {
    @Autowired
    private XareaMapper xareaMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private PatrolrecordMapper patrolrecordMapper;
    @Autowired
    private GpsMapper gpsMapper;

    /**
     * 根据大队，中队，岗位，中队领导
     *
     * @param battalion
     * @param detachment
     * @param station
     * @param leader
     * @return
     */
    @Override
    public ResultBean selectXareabycondition(String battalion, String detachment, String station, String leader, String grid, String type) {
        //查询出来的区域
        List<Xarea> xareas = xareaMapper.selectXareabycondition(battalion, detachment, station, leader, grid, type);
        List<Staff> zg = new ArrayList<>();
        List<Patrolrecord> ydr = new ArrayList<>();
        if (xareas.size() == 0) {
            return ResultUtil.setError(SystemCon.RERROR1, "error", null);
        }
        int conment = 0;
        int num = 0;
        List<Staff> staff = null;
        //查询该区域的所有民警
        for (int i = 0; i < xareas.size(); i++) {
            //区域内应在岗人数
            staff = staffMapper.selectStaffByxarea(xareas.get(i).getId());
            for (int k = 0; k < staff.size(); k++) {
                //实际在岗人数
                Patrolrecord patrolrecords = patrolrecordMapper.selectPatrolrecordByStaffId(staff.get(k).getSysStaffId());
                //不为空说明在岗
                if (patrolrecords != null) {
                    if (staff.get(k).getSysStaffId() == patrolrecords.getSysStaffId()) {
                        staff.get(k).setStaffOnline("1");
                        //获取gps点
                        Gps gps = gpsMapper.gpsEnd(staff.get(k).getSysStaffId());
                        num++;
                        staff.get(k).setGps(gps);
                    }

                }


            }
            conment += staff.size();
            xareas.get(i).setStaff(staff);

        }
        //System.out.println(xareas.size());
        return ResultUtil.setOK("success", xareas);
    }

    /**
     * 新增
     *
     * @param xarea
     * @return
     */
    @Override
    public ResultBean insertXarea(Xarea xarea) {
        return ResultUtil.execOp(xareaMapper.insertSelective(xarea), "新增");
    }

    @Override
    public ResultBean updateXarea(Xarea xarea) {
        return ResultUtil.execOp(xareaMapper.updateByPrimaryKeySelective(xarea), "修改");
    }

    @Override
    public ResultBean deleteXarea(Integer id) {
        return ResultUtil.execOp(xareaMapper.deleteByPrimaryKey(id), "删除");
    }

    @Override
    public ResultBean selectXareaByName(String name) {
        List<Xarea> xareas = xareaMapper.selectXareaByName(name);
        if (xareas.size() >= 0) {
            return ResultUtil.setOK("success", xareas);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    @Override
    public ResultBean selectXareaAll() {
        return ResultUtil.setOK("success", xareaMapper.selectXareaAll());
    }


    /**
     * 在线人数
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean selectonlineNumber(Integer id) {
        Map<String, Object> map = new HashMap<>();
        //根据id查询区域
        Xarea xarea = xareaMapper.selectByPrimaryKey(id);
        //查询区域里的应在岗民警
        List<Staff> staff = staffMapper.selectStaffByxarea(xarea.getId());
        //查询区域里实在岗人数
        List<Patrolrecord> online = new ArrayList<>();
        for (int k = 0; k < staff.size(); k++) {
            //实际在岗人数
            Patrolrecord patrolrecords = patrolrecordMapper.selectPatrolrecordByStaffId(staff.get(k).getSysStaffId());
            if (patrolrecords != null) {
                //获取gps点
                Gps gps = gpsMapper.gpsEnd(staff.get(k).getSysStaffId());
                online.add(patrolrecords);
                patrolrecords.setGpsdw(gps);
            }
        }
        map.put("yz", staff);
        map.put("sz", online);
        return ResultUtil.setOK("success", map);
    }

    //按大队显示所有警力
    @Override
    public ResultBean selectpolice() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        Map<String, Object> map5 = new HashMap<>();
        Map<String, Object> map6 = new HashMap<>();
        Map<String, Object> map7 = new HashMap<>();


        List<Staff> staff1 = staffMapper.selectStaffYdByAll(null, 25, null);

        List<Staff> staffsd1 = staffMapper.selectStaffByzg(null, null, "一大队");
        List<Staff> staff2 = staffMapper.selectStaffYdByAll(null, 26, null);
        List<Staff> staffsd2 = staffMapper.selectStaffByzg(null, null, "二大队");
        List<Staff> staff3 = staffMapper.selectStaffYdByAll(null, 27, null);
        List<Staff> staffsd3 = staffMapper.selectStaffByzg(null, null, "三大队");
        List<Staff> staff4 = staffMapper.selectStaffYdByAll(null, 28, null);
        List<Staff> staffsd4 = staffMapper.selectStaffByzg(null, null, "四大队");
        List<Staff> staff5 = staffMapper.selectStaffYdByAll(null, 29, null);
        List<Staff> staffsd5 = staffMapper.selectStaffByzg(null, null, "五大队");
        List<Staff> staff6 = staffMapper.selectStaffYdByAll(null, 30, null);
        List<Staff> staffsd6 = staffMapper.selectStaffByzg(null, null, "六大队");

        map1.put("id", 25);
        map1.put("name", "一大队");
        map1.put("ydnum", staff1.size());
        map1.put("sdnum", staffsd1.size());
        map2.put("id", 26);
        map2.put("name", "二大队");
        map2.put("ydnum", staff2.size());
        map2.put("sdnum", staffsd2.size());
        map3.put("id", 27);
        map3.put("name", "三大队");
        map3.put("ydnum", staff3.size());
        map3.put("sdnum", staffsd3.size());
        map4.put("id", 28);
        map4.put("name", "四大队");
        map4.put("ydnum", staff4.size());
        map4.put("sdnum", staffsd4.size());
        map5.put("id", 29);
        map5.put("name", "五大队");
        map5.put("ydnum", staff5.size());
        map5.put("sdnum", staffsd5.size());
        map6.put("id", 30);
        map6.put("name", "六大队");
        map6.put("ydnum", staff6.size());
        map6.put("sdnum", staffsd6.size());
        map7.put("id", 222);
        map7.put("name", "港区大队");
        map7.put("ydnum", 0);
        map7.put("sdnum", 0);

        List<Object> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        return ResultUtil.setOK("success", list);
    }

    @Override
    public ResultBean selectks() {
        List<Xarea> selectks = xareaMapper.selectks();
        if (selectks.size() >= 0) {
            return ResultUtil.setOK("success", selectks);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    @Override
    public ResultBean selectqt(String conment) {
        //查询出来的区域
        List<Xarea> xareas = xareaMapper.selectqt(conment);
        List<Staff> zg = new ArrayList<>();
        List<Patrolrecord> ydr = new ArrayList<>();
        if (xareas.size() == 0) {
            return ResultUtil.setError(SystemCon.RERROR1, "error", null);
        }
        int conments = 0;
        int num = 0;
        List<Staff> staff = null;
        //查询该区域的所有民警
        for (int i = 0; i < xareas.size(); i++) {
            //区域内应在岗人数
            staff = staffMapper.selectStaffByxarea(xareas.get(i).getId());
            for (int k = 0; k < staff.size(); k++) {
                //实际在岗人数
                Patrolrecord patrolrecords = patrolrecordMapper.selectPatrolrecordByStaffId(staff.get(k).getSysStaffId());

                if (patrolrecords != null) {
                    if (staff.get(k).getSysStaffId() == patrolrecords.getSysStaffId()) {
                        staff.get(k).setStaffOnline("1");
                        //获取gps点
                        Gps gps = gpsMapper.gpsEnd(staff.get(k).getSysStaffId());
                        num++;
                        staff.get(k).setGps(gps);
                    }

                }
            }
            conments += staff.size();
            xareas.get(i).setStaff(staff);
        }
        return ResultUtil.setOK("success", xareas);
    }

    /**
     * 日间警力部署
     * 固定岗每个大队几个人查询
     *
     * @return
     */
    @Override
    public ResultBean selectfixationRJ(String station) {
        //固定岗每个大队几个人查询
        List<HashMap> list = xareaMapper.selectfixationRJ(station);

        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //细分到中队
                List<HashMap> hashMaps1 = xareaMapper.selectfixationzdRJ(station, list.get(i).get("sectionName").toString());
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(sectionName.indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.countYDSum(station, dadui, substring);
                    hashMaps1.get(k).put("detachment", hashMaps);
                }

                list.get(i).put("detachment", hashMaps1);

            }

            return ResultUtil.setOK("success", list);
        }

        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 重点单位岗
     *
     * @return
     */
    @Override
    public ResultBean selectemphasisRJ() {
        //重点机关岗各个大队人数
        List<HashMap> list = xareaMapper.selectemphasisRJ();
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //各个中队人数
                List<HashMap> hashMaps1 = xareaMapper.selectemphasisZd();
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(sectionName.indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.selectemphasisPope(dadui, substring);

                    //详情添加到中队里
                    hashMaps1.get(k).put("detachment", hashMaps);
                }
                //中队详情添加到大队里
                list.get(i).put("detachment", hashMaps1);

            }

            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 日间铁骑
     *
     * @return
     */
    @Override
    public ResultBean selectcavalryRJ() {
        //铁骑
        List<HashMap> list = xareaMapper.selectcavalryRJ();
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                List<HashMap> hashMaps1 = xareaMapper.selectcavalryzdRJ();
                String dadui = hashMaps1.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                String substring = hashMaps1.get(i).get("sectionName").toString().substring(hashMaps1.get(i).get("sectionName").toString().indexOf("队") + 1, hashMaps1.get(i).get("sectionName").toString().length());
                List<HashMap> hashMaps = xareaMapper.countYxSum("2",dadui, substring);
                //详情添加到中队里
                hashMaps1.get(i).put("detachment", hashMaps);
                for (int j=0;j<hashMaps1.size();j++) {
                    Object detachment = hashMaps1.get(j).get("detachment");
                    if(detachment==null){
                        hashMaps1.get(j).remove("sectionName");
                        hashMaps1.get(j).remove("count");
                    }
                }
                for (int k=0;k<hashMaps1.size();k++) {
                    if(hashMaps1.get(k)==null){
                        hashMaps1.remove(hashMaps1.get(k));
                    }

                }
                list.get(i).put("detachment", hashMaps1);
                //中队详情添加到大队里


            }

            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 日间网格
     *
     * @return
     */
    @Override
    public ResultBean selectgriddingRJ() {
        //网格
        List<HashMap> list = xareaMapper.selectgriddingRJ();
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //List<HashMap> hashMaps = xareaMapper.selectgriddingPope(list.get(i).get("sectionName").toString());
                //各个中队人数
                List<HashMap> hashMaps1 = xareaMapper.selectgriddingZD();
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.selectgriddingPope(dadui, substring);

                    //详情添加到中队里
                    hashMaps1.get(k).put("detachment", hashMaps);
                }
                list.get(i).put("detachment", hashMaps1);

            }

            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 日间高速/快速岗
     *
     * @param station
     * @return
     */
    @Override
    public ResultBean selectexpresswayRJ(String station) {
        //高速
        List<HashMap> list = xareaMapper.selectexpresswayRJ("高速岗");
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //各个中队人数
                List<HashMap> hashMaps1 = xareaMapper.selectexpresswayZD();
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.selectexpresswayPope("高速岗",dadui, null,substring);
                    //详情添加到中队里
                    hashMaps1.get(k).put("detachment", hashMaps);
                }
                list.get(i).put("detachment", hashMaps1);

            }

            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 日间其他警力部署
     *
     * @return
     */
    @Override
    public ResultBean selectqtRJ() {
        //其他
        List<HashMap> list = xareaMapper.selectqtRJ();
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //各个中队人数
                List<HashMap> hashMaps1 = xareaMapper.selectqtZD();
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.selectqtPope(dadui,substring);

                    //详情添加到中队里
                    hashMaps1.get(k).put("detachment", hashMaps);
                }
              /*  for (int k=0;k<hashMaps1.size();k++) {
                    String detachment = hashMaps1.get(k).get("detachment").toString();
                    System.out.println(detachment);
                    if(detachment==null || detachment.equals("")){
                        hashMaps1.get(k).remove("sectionName");
                        hashMaps1.get(k).remove("count");
                    }
                }*/
                for (int j=0;j<hashMaps1.size();j++) {
                    String detachment = hashMaps1.get(j).get("sectionName").toString().substring(0, hashMaps1.get(j).get("sectionName").toString().indexOf("队") + 1);
                    if(!detachment.equals(list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1))){
                        hashMaps1.get(j).remove("sectionName");
                        hashMaps1.get(j).remove("count");
                        hashMaps1.get(j).remove("detachment");
                    }
                }
                list.get(i).put("detachment", hashMaps1);

            }
            return ResultUtil.setOK("success", list);

        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     *
     * 全部在线人警力部署
     *
     * @return
     */
    @Override
    public ResultBean selectInformant() {
        //全部在线人
        List<HashMap> list = xareaMapper.selectInformant();
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //各个中队人数
                List<HashMap> hashMaps1 = xareaMapper.selectInformantZD();
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.selectInformantPope(dadui,substring);
                    //详情添加到中队里
                    System.out.println(hashMaps.size());
                    if(dadui.equals(list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1))){
                        hashMaps1.get(k).put("detachment", hashMaps);
                    }

                }
                for (int j=0;j<hashMaps1.size();j++) {
                    Object detachment = hashMaps1.get(j).get("detachment");
                    if(detachment==null){
                        hashMaps1.get(j).remove("sectionName");
                        hashMaps1.get(j).remove("count");
                    }
                }

                list.get(i).put("detachment", hashMaps1);

            }

            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 夜间夜巡
     *
     * @return
     */
    @Override
    public ResultBean selectNightTour() {
        //夜间夜巡
        List<HashMap> list = xareaMapper.selectNightTour();
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //各个中队人数
                List<HashMap> hashMaps1 = xareaMapper.selectNightTourZD();
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.selectNightTourPope(dadui,substring);
                    //详情添加到中队里
                    hashMaps1.get(k).put("detachment", hashMaps);
                }
                list.get(i).put("detachment", hashMaps1);

            }

            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 夜间快速/高速警力部署
     *
     * @param station
     * @return
     */
    @Override
    public ResultBean selectcelerity(String station) {
        //夜间快速/高速警力部署
        List<HashMap> list = xareaMapper.selectcelerity(station);
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //各个中队人数
                List<HashMap> hashMaps1 = xareaMapper.selectcelerityPopeZD();
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.selectcelerityPope(station,dadui,substring);
                    //详情添加到中队里
                    hashMaps1.get(k).put("detachment", hashMaps);
                }
                list.get(i).put("detachment", hashMaps1);

            }

            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 夜间其他警力部署
     *
     * @return
     */
    @Override
    public ResultBean selectqita() {
        //夜间其他警力部署
        List<HashMap> list = xareaMapper.selectqita();
        if (list.size() >= 0) {
            for (int i = 0; i < list.size(); i++) {
                String sectionName = list.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1);
                    list.get(i).put("sectionName", sectionName1);
                }
                //各个中队人数
                List<HashMap> hashMaps1 = xareaMapper.selectqita();
                for (int k = 0; k < hashMaps1.size(); k++) {
                    String dadui = hashMaps1.get(k).get("sectionName").toString().substring(0, hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1);
                    String substring = hashMaps1.get(k).get("sectionName").toString().substring(hashMaps1.get(k).get("sectionName").toString().indexOf("队") + 1, hashMaps1.get(k).get("sectionName").toString().length());
                    List<HashMap> hashMaps = xareaMapper.selectqitaPope(dadui,substring);
                    //详情添加到中队里
                    if(dadui.equals(list.get(i).get("sectionName").toString().substring(0, sectionName.indexOf("队") + 1))){
                        hashMaps1.get(k).put("detachment", hashMaps);
                    }

                }
                for (int j=0;j<hashMaps1.size();j++) {
                    Object detachment = hashMaps1.get(j).get("detachment");
                    if(detachment==null){
                        hashMaps1.get(j).remove("sectionName");
                        hashMaps1.get(j).remove("count");
                    }
                    if(hashMaps1.get(j).get("detachment")!=null){
                        list.get(i).put("detachment", hashMaps1);
                    }
                }



            }

            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 九主六块十六示范区
     *
     * @param station
     * @return
     */
    @Override
    public ResultBean selectDemonstrationPlot(String station) {
        List<Xarea> xareas = xareaMapper.selectDemonstrationPlot(station);
        if (xareas.size() >= 0) {
            return ResultUtil.setOK("success", xareas);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);

    }

    /**
     * 根据区域名字模糊匹配部署警力
     *
     * @param name
     * @return
     */
    @Override
    public ResultBean selctStrength(String name) {
        List<HashMap> hashMaps = xareaMapper.selctStrength(name,null);
        if (hashMaps.size() >= 0) {
            return ResultUtil.setOK("success", hashMaps);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 九主六块多有人
     *
     * @return
     */
    @Override
    public ResultBean selectAllByDemonstration(String station) {
        //根据九主六块查出的区域
        List<Xarea> xareas = xareaMapper.selectDemonstrationPlot(station);
        List<String> ddNames = xareaMapper.findDd();
        List<HashMap> qyList = new ArrayList<>();
        for (Xarea xarea : xareas) {
            HashMap<String, Object> qyMap = new HashMap<>();
            List<HashMap> ddList = new ArrayList<>();
            for (String ddName : ddNames) {
                HashMap<String, Object> ddMap = new HashMap<>();
                ddMap.put("sectionName", ddName);
                //根据区域名字模糊匹配部署警力
                List<HashMap> list = xareaMapper.selctStrength(xarea.getGridding(),ddName);
                ddMap.put("count", list.size());
                ddMap.put("detachment", list);
                ddList.add(ddMap);
            }

            qyMap.put("ddList",ddList);
            qyMap.put("qyData",xarea.getGridding());
            qyList.add(qyMap);

        }


        /*List<HashMap> resultList = new ArrayList<>();

        for (String sectionName : ddNames) {
            HashMap<String, Object> ddMap = new HashMap<>();
            List<HashMap> dDList = new ArrayList<>();
            ddMap.put("sectionName", sectionName);
            for (HashMap list : listAll) {
                if (list.get("battalion").equals(sectionName)) {
                    dDList.add(list);
                }
            }
            ddMap.put("count", dDList.size());
            ddMap.put("detachment", dDList);

            resultList.add(ddMap);
        }*/

        return ResultUtil.setOK("success", qyList);
    }


}
