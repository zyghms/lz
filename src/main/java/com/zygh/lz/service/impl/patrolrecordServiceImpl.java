package com.zygh.lz.service.impl;

import com.zygh.lz.admin.Gps;
import com.zygh.lz.admin.Patrolrecord;
import com.zygh.lz.admin.Problem;
import com.zygh.lz.admin.Staff;
import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.mapper.*;
import com.zygh.lz.service.patrolrecordService;
import com.zygh.lz.util.DataTime;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class patrolrecordServiceImpl implements patrolrecordService {

    @Autowired
    private PatrolrecordMapper patrolrecordMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private GpsMapper gpsMapper;
    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private XareaMapper xareaMapper;

    /**
     * 新增巡查记录
     *
     * @param patrolrecord
     * @return
     */
    @Override
    public ResultBean addPatrolrecord(Patrolrecord patrolrecord) {

        int i = patrolrecordMapper.insertSelective(patrolrecord);
        if (i > 0) {
            Patrolrecord patrolrecord1 = patrolrecordMapper.selectBylast();
            //返回巡查记录id
            return ResultUtil.setOK("success", patrolrecord1.getSysPatrolRecordId());
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 修改巡查记录
     *
     * @param patrolrecord
     * @return
     */
    @Override
    public ResultBean updatePatrolrecord(Patrolrecord patrolrecord) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Integer sysPatrolRecordId = patrolrecord.getSysPatrolRecordId();
        Patrolrecord patrolrecord1 = patrolrecordMapper.selectByPrimaryKey(sysPatrolRecordId);
        String patrolRecordGps = patrolrecord1.getPatrolRecordGps();
        System.out.println("patro:" + patrolRecordGps);
        if(patrolrecord.getPatrolRecordGps()!=null){
            if (patrolRecordGps != null && !patrolRecordGps.equals("")) {
                patrolrecord.setPatrolRecordGps(patrolRecordGps + "," + patrolrecord.getPatrolRecordGps());
            } else {
                patrolrecord.setPatrolRecordGps(patrolRecordGps + patrolrecord.getPatrolRecordGps());
            }
        }

        System.out.println("=========" + patrolRecordGps + patrolrecord.getPatrolRecordGps());
        return ResultUtil.execOp(patrolrecordMapper.updateByPrimaryKeySelective(patrolrecord), "修改");
    }

    /**
     * 根据id删除巡查记录
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean delPatrolrecord(Integer id) {
        return ResultUtil.execOp(patrolrecordMapper.deleteByPrimaryKey(id), "删除");
    }

    /**
     * 根据道路类型、部门查询所有巡查信息(分层查询)
     *
     * @param staffid
     * @return
     */
    @Override
    public ResultBean selectByRoadtype(Integer staffid, String beginTime, String endTime) {

        //登陆人信息
        Staff staff = staffMapper.selectByPrimaryKey(staffid);
        if (staff == null) {
            return ResultUtil.setError(SystemCon.RERROR1, "请传入登录人主键", null);
        }
        //提取个人信息中得所属大队
        Integer sysSectionId = staff.getSysSectionId();
        //提取个人信息中的路长等级
        String staffHierarchy = staff.getStaffHierarchy();
        if (staffHierarchy.equals("二级路长") || staffHierarchy.equals("一级路长")) {
            List<Patrolrecord> patrolrecords2 = patrolrecordMapper.selectBysysStaffId(staffid, beginTime, endTime);
            if (patrolrecords2.size() > 0) {
                for (Patrolrecord patrolrecord : patrolrecords2) {
                    Integer staffID = patrolrecord.getSysStaffId();
                    System.out.println();
                    int sysPatrolRecordId = patrolrecord.getSysPatrolRecordId();
                    Integer problemid = patrolrecord.getSysPatrolRecordId();
                    List<Gps> gpsList = gpsMapper.selectGpsByRecord(staffID, sysPatrolRecordId);
                    List<Problem> problemList = problemMapper.selectProblemByRecord(problemid);
                    patrolrecord.setGpsList(gpsList);
                    patrolrecord.setProblemList(problemList);
                }
                return ResultUtil.setOK("success", patrolrecords2);
            }

            return ResultUtil.setError(SystemCon.RERROR1, "error", null);

        } else if (staffHierarchy.equals("总路长") || staffHierarchy.equals("分管路长")) {
            List<Patrolrecord> patrolrecordList = patrolrecordMapper.selectByRoadtype(sysSectionId, beginTime, endTime);
            System.out.println(patrolrecordList);
            if (patrolrecordList.size() > 0) {
                for (Patrolrecord patrolrecord : patrolrecordList) {
                    Integer staffID = patrolrecord.getSysStaffId();
                    int sysPatrolRecordId = patrolrecord.getSysPatrolRecordId();
                    Integer problemid = patrolrecord.getSysPatrolRecordId();
                    List<Gps> gpsList = gpsMapper.selectGpsByRecord(staffID, sysPatrolRecordId);
                    List<Problem> problemList = problemMapper.selectProblemByRecord(problemid);
                    patrolrecord.setGpsList(gpsList);
                    patrolrecord.setProblemList(problemList);
                }
                return ResultUtil.setOK("success", patrolrecordList);
            }
            return ResultUtil.setError(SystemCon.RERROR1, "error", null);
        } else {
            sysSectionId = null;
            List<Patrolrecord> patrolrecordList = patrolrecordMapper.selectByRoadtype(sysSectionId, beginTime, endTime);
            if (patrolrecordList.size() > 0) {
                for (Patrolrecord patrolrecord : patrolrecordList) {
                    Integer staffID = patrolrecord.getSysStaffId();
                    int sysPatrolRecordId = patrolrecord.getSysPatrolRecordId();
                    Integer problemid = patrolrecord.getSysPatrolRecordId();
                    List<Gps> gpsList = gpsMapper.selectGpsByRecord(staffID, sysPatrolRecordId);
                    List<Problem> problemList = problemMapper.selectProblemByRecord(problemid);
                    patrolrecord.setGpsList(gpsList);
                    patrolrecord.setProblemList(problemList);
                }
                return ResultUtil.setOK("success", patrolrecordList);
            } else {
                return ResultUtil.setError(SystemCon.RERROR1, "error", null);
            }


        }


    }

    @Override
    public ResultBean selectByhierarchy(String staffHierarchy, Integer sysSectionId, String roadType) {
        return null;
    }

    @Override
    public ResultBean selectBysysStaffId(Integer staffid, String roadType) {
        return null;
    }

    @Override
    public ResultBean updatePatrolrecordBysysStaffId(Patrolrecord patrolrecord) {
        return null;
    }

    @Override
    public ResultBean selectBypatrolstate(Integer staffid, String roadType) {
        return null;
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public ResultBean countPatrolrecord(String startTime, String endTime) throws Exception {
        //处理时间
        Date startDate = simpleDateFormat.parse(startTime);
        Date endDate = simpleDateFormat.parse(endTime);

        List<Map> list = new ArrayList<>();

        int one1 = patrolrecordMapper.countPatrolrecord("一大队", "主干道", startDate, endDate);
        int one2 = patrolrecordMapper.countPatrolrecord("一大队", "拥堵路段", startDate, endDate);
        int one3 = patrolrecordMapper.countPatrolrecord("一大队", "施工路段", startDate, endDate);
        int one4 = one1 + one2 + one3;
        Map<String, Object> map1 = new HashMap<>();
        map1.put("zgd", one1);
        map1.put("ydld", one2);
        map1.put("sgld", one3);
        map1.put("sum", one4);
        Map<String, Object> map11 = new HashMap<>();
        map11.put("sectionName", "一大队");
        map11.put("staffHierarchay", map1);
        list.add(map11);

        int two1 = patrolrecordMapper.countPatrolrecord("二大队", "主干道", startDate, endDate);
        int two2 = patrolrecordMapper.countPatrolrecord("二大队", "拥堵路段", startDate, endDate);
        int two3 = patrolrecordMapper.countPatrolrecord("二大队", "施工路段", startDate, endDate);
        int two4 = two1 + two2 + two3;
        Map<String, Object> map2 = new HashMap<>();
        map2.put("zgd", two1);
        map2.put("ydld", two2);
        map2.put("sgld", two3);
        map2.put("sum", two4);
        Map<String, Object> map22 = new HashMap<>();
        map22.put("sectionName", "二大队");
        map22.put("staffHierarchay", map2);
        list.add(map22);

        int three1 = patrolrecordMapper.countPatrolrecord("三大队", "主干道", startDate, endDate);
        int three2 = patrolrecordMapper.countPatrolrecord("三大队", "拥堵路段", startDate, endDate);
        int three3 = patrolrecordMapper.countPatrolrecord("三大队", "施工路段", startDate, endDate);
        int three4 = three1 + three2 + three3;
        Map<String, Object> map3 = new HashMap<>();
        map3.put("zgd", three1);
        map3.put("ydld", three2);
        map3.put("sgld", three3);
        map3.put("sum", three4);
        Map<String, Object> map33 = new HashMap<>();
        map33.put("sectionName", "三大队");
        map33.put("staffHierarchay", map3);
        list.add(map33);

        int four1 = patrolrecordMapper.countPatrolrecord("四大队", "主干道", startDate, endDate);
        int four2 = patrolrecordMapper.countPatrolrecord("四大队", "拥堵路段", startDate, endDate);
        int four3 = patrolrecordMapper.countPatrolrecord("四大队", "施工路段", startDate, endDate);
        int four4 = four1 + four2 + four3;
        Map<String, Object> map4 = new HashMap<>();
        map4.put("zgd", four1);
        map4.put("ydld", four2);
        map4.put("sgld", four3);
        map4.put("sum", four4);
        Map<String, Object> map44 = new HashMap<>();
        map44.put("sectionName", "四大队");
        map44.put("staffHierarchay", map4);
        list.add(map44);

        int five1 = patrolrecordMapper.countPatrolrecord("五大队", "主干道", startDate, endDate);
        int five2 = patrolrecordMapper.countPatrolrecord("五大队", "拥堵路段", startDate, endDate);
        int five3 = patrolrecordMapper.countPatrolrecord("五大队", "施工路段", startDate, endDate);
        int five4 = five1 + five2 + five3;
        Map<String, Object> map5 = new HashMap<>();
        map5.put("zgd", five1);
        map5.put("ydld", five2);
        map5.put("sgld", five3);
        map5.put("sum", five4);
        Map<String, Object> map55 = new HashMap<>();
        map55.put("sectionName", "五大队");
        map55.put("staffHierarchay", map5);
        list.add(map55);

        int sex1 = patrolrecordMapper.countPatrolrecord("六大队", "主干道", startDate, endDate);
        int sex2 = patrolrecordMapper.countPatrolrecord("六大队", "拥堵路段", startDate, endDate);
        int sex3 = patrolrecordMapper.countPatrolrecord("六大队", "施工路段", startDate, endDate);
        int sex4 = sex1 + sex2 + sex3;
        Map<String, Object> map6 = new HashMap<>();
        map6.put("zgd", sex1);
        map6.put("ydld", sex2);
        map6.put("sgld", sex3);
        map6.put("sum", sex4);
        Map<String, Object> map66 = new HashMap<>();
        map66.put("sectionName", "六大队");
        map66.put("staffHierarchay", map6);
        list.add(map66);

        return ResultUtil.setOK("success", list);
    }

    /**
     * 查询所有巡查记录
     *
     * @return
     */
    @Override
    public ResultBean selectAllPatrolrecord() {
        List<Patrolrecord> patrolrecords = patrolrecordMapper.selectAllPatrolrecord();
        if (patrolrecords.size() > 0) {
            return ResultUtil.setOK("success", patrolrecords);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 查询所有巡查记录的视频
     *
     * @return
     */
    @Override
    public ResultBean selectVideo() {
        List<Patrolrecord> patrolrecords = patrolrecordMapper.selectVideo();
        if (patrolrecords.size() > 0) {
            List<Patrolrecord> list = new ArrayList<>();
            for (int i = 0; i < patrolrecords.size(); i++) {
                String[] strArray = null;
                String patrolRecordVideo = patrolrecords.get(i).getPatrolRecordVideo();
                //切割
                strArray = patrolRecordVideo.split("\\,");
                if (strArray.length > 0) {
                    for (int j = 0; j < strArray.length; j++) {
                        Patrolrecord patrolrecord = new Patrolrecord();
                        //拷贝
                        BeanUtils.copyProperties(patrolrecords.get(i), patrolrecord);
                        patrolrecord.setPatrolRecordVideo(strArray[j]);
                        list.add(patrolrecord);
                    }

                }
            }
            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 根据开始时间，结束时间、道路名称查询巡查记录
     *
     * @return
     */
    @Override
    public ResultBean selectDim(String roadName, String beginTime, String endTime) {
        List<Object> patrolrecords = patrolrecordMapper.selectDim(roadName, beginTime, endTime);
        if (patrolrecords.size() > 0) {
            return ResultUtil.setOK("success", patrolrecords);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 根据时间查询个人巡查记录
     *
     * @param SysStaffId
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public ResultBean selectByStaff(Integer SysStaffId, String beginTime, String endTime) {
        List<Map<String, Object>> patrolrecordList = patrolrecordMapper.selectByStaff(SysStaffId, beginTime, endTime);
        Map<String, Object> mape = new HashMap<>();
        List<Gps> gpsList1 = null;
        List<Problem> problemList = null;
        if (patrolrecordList.size() > 0) {
            for (int i = 0; i < patrolrecordList.size(); i++) {
                Integer problemid = (Integer) patrolrecordList.get(i).get("sys_patrol_record_id");
                if (SysStaffId != null && problemid != null) {
                    gpsList1 = gpsMapper.selectGpsByRecord(SysStaffId, problemid);
                }
                if (gpsList1.size() != 0) {
                    patrolrecordList.get(i).put("gpsList", gpsList1);
                    if (problemMapper.selectProblemByRecord(problemid) != null) {
                        problemList = problemMapper.selectProblemByRecord(problemid);
                        for (int j = 0; j < problemList.size(); j++) {
                            mape.put("problemList", problemList);
                        }
                    }
                } else {
                    continue;
                }
                patrolrecordList.get(i).put("problemList", problemList);

            }
            if (patrolrecordList.size() > 0 || patrolrecordList.size() == 0) {
                return ResultUtil.setOK("success", patrolrecordList);
            }

        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    @Override
    public ResultBean selectByStaffapp(Integer SysStaffId, String beginTime, String endTime) {
        List<Map<String, Object>> patrolrecordList = patrolrecordMapper.selectByStaff(SysStaffId, beginTime, endTime);
        String arr[] = new String[20];
        if (patrolrecordList.size() > 0) {
            for (int i = 0; i < patrolrecordList.size(); i++) {
                Map<String, Object> map = patrolrecordList.get(i);
                Integer problemid = (Integer) map.get("sys_patrol_record_id");
                if (problemMapper.selectProblemByRecord(problemid) != null) {
                    List<Problem> problemList = problemMapper.selectProblemByRecord(problemid);
                    Map<String, Object> maps = new HashMap<>();
                    for (int c = 0; c < problemList.size(); c++) {
                        maps.put("problemList", problemList);
                    }
                }
            }
            if (patrolrecordList.size() > 0 || patrolrecordList.size() == 0) {
                return ResultUtil.setOK("success", patrolrecordList);
            }
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 查询各个大队的巡查记录个数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public ResultBean selectRecordNum(String startDate, String endDate) {
        List<Patrolrecord> patrolrecords = patrolrecordMapper.selectRecordNum(startDate, endDate);
        if (patrolrecords.size() > 0) {
            return ResultUtil.setOK("success", patrolrecords);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 根据登录用户负责道路列表
     *
     * @param staffId
     * @return
     */
    @Override
    public ResultBean selectByStaffId(Integer staffId) {
        Staff staff = staffMapper.selectInfoByid(staffId);
        List<Object> list = null;
        if (staff.getStaffHierarchy().equals("一级路长")) {
            list = patrolrecordMapper.selectByStaffId(staffId);
        } else if (staff.getStaffHierarchy().equals("二级路长")) {
            list = patrolrecordMapper.selectBytwostaffId(staffId);
        } else {
            return ResultUtil.setError(SystemCon.RERROR1, "error", null);
        }
        if (list.size() > 0 || list.size() == 0) {
            return ResultUtil.setOK("success", list);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 批量删除
     *
     * @param array
     * @return
     */
    @Override
    public ResultBean delectPatrolrecordById(int[] array) {
        return ResultUtil.execOp(patrolrecordMapper.delectPatrolrecordById(array), "批量删除");
    }

    /**
     * 根据id查询该人的巡查记录
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean selectAllPatrolrecordById(Integer id) {
        List<Patrolrecord> patrolrecords = patrolrecordMapper.selectAllPatrolrecordById(id);
        if (patrolrecords.size() > 0 || patrolrecords.size() == 0) {
            return ResultUtil.setOK("success", patrolrecords);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }


    private List<Staff> getChild(Integer id, List<Staff> rootMenu) throws NullPointerException {//int id  id 是指当前菜单id，rootMenu是指要查找的列表
        List<Staff> childList = new ArrayList<>();
        Iterator iterList = rootMenu.iterator();
        while (iterList.hasNext()) {
            Staff treemenu = (Staff) iterList.next();
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (treemenu.getStafffPid() != 0) {
                if (treemenu.getStafffPid() == id) {
                    childList.add(treemenu);
                }
            }
            System.out.println();
        }
        // 把子菜单的子菜单再循环一遍
        /*for (Treemenu treemenu : childList) {*/
        Iterator iterList2 = childList.iterator();
        while (iterList2.hasNext()) {
            Staff treemenu = (Staff) iterList2.next();
            treemenu.setStaffList(getChild(treemenu.getSysStaffId(), rootMenu));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }


    public List<HashMap> theDaySum(String battalion) throws Exception{
        List<HashMap> daySumList = new ArrayList<>();

        List<HashMap> peopleList = new ArrayList<>();

        //早高峰实到应到在线率
        HashMap<String, Object> zgfMap = new HashMap<>();
        //固定岗。。。。。。。
        HashMap<String, Object> gdMap = new HashMap<>();
        //晚高峰。。。。。。。
        HashMap<String, Object> wgfMap = new HashMap<>();
        //夜巡。。。。。。。
        HashMap<String, Object> yxMap = new HashMap<>();
        //特殊
        HashMap<String, Object> tsMap = new HashMap<>();




        //夜巡 3   铁骑2
        List<HashMap> yxPeoples = xareaMapper.countYxSum("3", battalion);
        int yxYDsum = yxPeoples.size();

        //高峰应到数
        List<HashMap> gfPeoples = xareaMapper.countYDSum("高峰岗", battalion);
        int gfYDsum = gfPeoples.size();

        //日常勤务
        List<HashMap> rcPeoples = xareaMapper.countRcYDsum(battalion);
        int rcYDsum = (rcPeoples.size())/2;


        int yxSDsum = patrolrecordMapper.countYxSDsum(battalion).size();
        int gfSDsum = patrolrecordMapper.countGdorGfSDsum("高峰岗",battalion).size();
        int rcSDsum = patrolrecordMapper.countRcSDsum(battalion).size();


        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String dayTime=df.format(new Date());// new Date()为获取当前系统时间

        Date startTime = ft.parse(dayTime+" 07:30:00");
        Date endTime = ft.parse(dayTime+" 09:00:00");
        Date startTime2 = ft.parse(dayTime+" 17:30:00");
        Date endTime2 = ft.parse(dayTime+" 18:30:00");
        Date startTime3 = ft.parse(dayTime+" 07:00:00");
        Date endTime3 = ft.parse(dayTime+" 20:00:00");
        Date nowTime = new Date();
        //早高峰
        boolean effectiveDate = isEffectiveDate(nowTime, startTime, endTime);
        //晚高峰
        boolean effectiveDate2 = isEffectiveDate(nowTime, startTime2, endTime2);
        //固定岗
        boolean effectiveDate3 = isEffectiveDate(nowTime, startTime3, endTime3);

        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);

        if (effectiveDate3){
            //如果在早高峰
            if (effectiveDate){

                /*//早高峰
                zgfMap.put("type","早高峰");
                zgfMap.put("time","07:30-09:00");
                zgfMap.put("gfYDsum",gfYDsum);
                zgfMap.put("gfSDsum",gfSDsum);
                zgfMap.put("gfZXL",numberFormat.format((float)gfSDsum/(float)gfYDsum*100));*/

                int zgfYDsum = rcYDsum+gfYDsum;
                int zgfSDsum = rcSDsum+gfSDsum;

                peopleList.addAll(gfPeoples);
                peopleList.addAll(rcPeoples);

                gdMap.put("type","日常勤务");
                gdMap.put("time","早高峰");
                gdMap.put("gfYDsum",zgfYDsum);
                gdMap.put("gfSDsum",zgfSDsum);
                gdMap.put("gfZXL",numberFormat.format((float)zgfSDsum/(float)zgfYDsum*100));
                gdMap.put("people",peopleList);

                tsMap.put("type","特殊勤务");
                tsMap.put("time","08:00-20:00");
                tsMap.put("gfYDsum",0);
                tsMap.put("gfSDsum",0);
                tsMap.put("gfZXL",0);




                //daySumList.add(zgfMap);
                daySumList.add(gdMap);
                daySumList.add(tsMap);



            }else if (effectiveDate2){

                int wgfYDsum = rcYDsum+gfYDsum;
                int wgfSDsum = rcSDsum+gfSDsum;

                peopleList.addAll(gfPeoples);
                peopleList.addAll(rcPeoples);

                /*wgfMap.put("type","晚高峰");
                wgfMap.put("time","17:30-18:30");
                wgfMap.put("gfYDsum",gfYDsum);
                wgfMap.put("gfSDsum",gfSDsum);
                wgfMap.put("gfZXL",numberFormat.format((float)gfSDsum/(float)gfYDsum*100));*/

                //晚高峰
                gdMap.put("type","日常勤务");
                gdMap.put("time","晚高峰");
                gdMap.put("gfYDsum",wgfYDsum);
                gdMap.put("gfSDsum",wgfSDsum);
                gdMap.put("gfZXL",numberFormat.format((float)wgfSDsum/(float)wgfYDsum*100));
                gdMap.put("people",peopleList);

                tsMap.put("type","特殊勤务");
                tsMap.put("time","08:00-20:00");
                tsMap.put("gfYDsum",0);
                tsMap.put("gfSDsum",0);
                tsMap.put("gfZXL",0);




                //daySumList.add(wgfMap);
                daySumList.add(gdMap);
                daySumList.add(tsMap);

            }else {

                peopleList.addAll(rcPeoples);

                gdMap.put("type","日常勤务");
                gdMap.put("time","平峰期");
                gdMap.put("gfYDsum",rcYDsum);
                gdMap.put("gfSDsum",rcSDsum);
                gdMap.put("gfZXL",numberFormat.format((float)rcSDsum/(float)rcYDsum*100));
                gdMap.put("people",peopleList);


                tsMap.put("type","特殊勤务");
                tsMap.put("time","08:00-20:00");
                tsMap.put("gfYDsum",0);
                tsMap.put("gfSDsum",0);
                tsMap.put("gfZXL",0);




                daySumList.add(gdMap);
                daySumList.add(tsMap);

            }
        }else {
            peopleList.addAll(yxPeoples);

            yxMap.put("type","夜巡");
            yxMap.put("time","20:00-次日07:00");
            yxMap.put("gfYDsum",yxYDsum);
            yxMap.put("gfSDsum",yxSDsum);
            yxMap.put("gfZXL",numberFormat.format((float)yxSDsum/(float)yxYDsum*100));
            yxMap.put("people",peopleList);

            tsMap.put("type","特殊勤务");
            tsMap.put("time","20:00-次日07:00");
            tsMap.put("gfYDsum",0);
            tsMap.put("gfSDsum",0);
            tsMap.put("gfZXL",0);



            daySumList.add(yxMap);
            daySumList.add(tsMap);

        }

        return daySumList;
    }



    public List<HashMap> typeSum(String battalion) throws Exception{
        List<HashMap> typeSumList = new ArrayList<>();
        //固定组数、应到数
        HashMap<String, Object> gdMap = new HashMap<>();
        //高峰岗组数、应到数
        HashMap<String, Object> gfMap = new HashMap<>();
        //铁骑实组数、应到数
        HashMap<String, Object> tqMap = new HashMap<>();
        //网格实组数、应到数
        HashMap<String, Object> wgMap = new HashMap<>();
        //重点组、应到实到
        HashMap<String, Object> zdMap = new HashMap<>();
        //其他组、应到实到
        HashMap<String, Object> qtMap = new HashMap<>();
        //高速
        HashMap<String, Object> gsMap = new HashMap<>();
        //快速
        HashMap<String, Object> ksMap = new HashMap<>();
        //特勤
        HashMap<String, Object> TQMap = new HashMap<>();

        //应到
        //固定岗
        int gdYDsum = xareaMapper.countYDSum("固定岗",battalion).size();
        //高峰岗
        int gfYDsum = xareaMapper.countYDSum("高峰岗",battalion).size();
        //铁骑2  夜巡3
        int tqYDsum = xareaMapper.countYxSum("2",battalion).size();
        //网格
        int wgYDsum = xareaMapper.countWgSum(battalion).size();
        //重点
        int zdYDsum = xareaMapper.countZdYDSum(battalion).size();
        //其他
        int qtYDsum = xareaMapper.countQtYDSum(battalion).size();


        int tqZnum = xareaMapper.countTqZSum(battalion).size();
        int gdZnum = xareaMapper.countggZSum("固定岗",battalion);
        int gfZnum = xareaMapper.countggZSum("高峰岗",battalion);
        int wgZnum = xareaMapper.countWgZSum(battalion);
        int zdZnum = xareaMapper.countZdZSum(battalion);//重点岗
        int qtZnum = xareaMapper.countQtZSum(battalion);//其他岗


        int gdSD = patrolrecordMapper.countGdorGfSDsum("固定岗",battalion).size();
        int gfSD = patrolrecordMapper.countGdorGfSDsum("高峰岗",battalion).size();
        int tqSD = patrolrecordMapper.countTqSDsum(battalion).size();
        int wgSD = patrolrecordMapper.countWgSDsum(battalion).size();
        int zdSD = patrolrecordMapper.countZdSDsum(battalion).size();//重点
        int qtSD = patrolrecordMapper.countQtSDsum(battalion).size();


        gdMap.put("name","固定岗");
        gdMap.put("YDnum",gdYDsum);
        gdMap.put("Znum",gdZnum);
        gdMap.put("SDnum",gdSD);

        gfMap.put("name","高峰岗");
        gfMap.put("YDnum",gfYDsum);
        gfMap.put("Znum",gfZnum);
        gfMap.put("SDnum",gfSD);

        tqMap.put("name","铁骑");
        tqMap.put("YDnum",tqYDsum);
        tqMap.put("Znum",tqZnum);
        tqMap.put("SDnum",tqSD);

        wgMap.put("name","网格");
        wgMap.put("YDnum",wgYDsum);
        wgMap.put("Znum",wgZnum);
        wgMap.put("SDnum",wgSD);

        zdMap.put("name","重点");
        zdMap.put("YDnum",zdYDsum);
        zdMap.put("Znum",zdZnum);
        zdMap.put("SDnum",zdSD);

        qtMap.put("name","其他");
        qtMap.put("YDnum",qtYDsum);
        qtMap.put("Znum",qtZnum);
        qtMap.put("SDnum",qtSD);

        typeSumList.add(gdMap);
        typeSumList.add(gfMap);
        typeSumList.add(tqMap);
        typeSumList.add(wgMap);
        typeSumList.add(zdMap);
        typeSumList.add(qtMap);
        return typeSumList;
    }





    /**
     *
     * @param nowTime   当前时间
     * @param startTime	开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        System.out.println();
        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


}

