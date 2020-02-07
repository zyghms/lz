package com.zygh.lz.service.impl;

import com.zygh.lz.admin.*;
import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.mapper.*;
import com.zygh.lz.service.staffService;
import com.zygh.lz.util.DataTime;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.util.md5;
import com.zygh.lz.vo.ResultBean;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class staffServiceImpl implements staffService {
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SubsystemMapper subsystemMapper;
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private GpsMapper gpsMapper;
    @Autowired
    private XareaMapper xareaMapper;
    @Autowired
    private PatrolrecordMapper patrolrecordMapper;

    /**
     * 登录
     *
     * @param staff_tel
     * @param staff_password
     * @return
     */
    @Override
    public ResultBean selectByLogin(String staff_tel, String staff_password) {
        //加密
        staff_password = md5.MD5Encode(staff_password, "utf-8");
        //根据用户密码查询
        Staff staff = staffMapper.selectByLogin(staff_tel, staff_password);
        if (staff != null) {
            //查询个人角色
            Role role = roleMapper.selectByPrimaryKey(staff.getSysRoleId());
            String subsystemuse = role.getSubsystemUsetype();
            String[] subsystemuses = subsystemuse.split(",");
            List<Subsystem> subsystems = new ArrayList<Subsystem>();
            List<Menu> menus = new ArrayList<Menu>();
            for (int i = 0; i < subsystemuses.length; i++) {
                int subsystemid = Integer.valueOf(subsystemuses[i]);
                //查询该人使用的子系统
                Subsystem subsystem = subsystemMapper.selectByPrimaryKey(subsystemid);
                subsystems.add(subsystem);
            }
            LinkedHashMap<String, Object> json = new LinkedHashMap<>();
            json.put("staff", staff);
            json.put("subsystems", subsystems);
            return ResultUtil.setOK("登录成功", json);
        }
        return ResultUtil.setOK("登录失败", null);
    }

    /**
     * 注册
     *
     * @param staff
     * @return
     */
    @Override
    public ResultBean register(Staff staff) {
        md5.MD5Encode(staff.getStaffPassword(), "utf-8");
        return ResultUtil.execOp(staffMapper.insertSelective(staff), "注册");
    }

    /**
     * 校验用户是否存在
     *
     * @param name
     * @return
     */
    @Override
    public ResultBean usercheck(String name) {
        Staff staff = staffMapper.selectByName(name);
        if (staff == null) {
            ResultUtil.setOK("success", null);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "该用户已存在", staff);
    }

    /**
     * 根据id修改
     *
     * @param staff
     * @return
     */
    @Override
    public ResultBean updaStaffInfoById(Staff staff) {
        return ResultUtil.execOp(staffMapper.updateByPrimaryKeySelective(staff), "修改");
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean delStaffInfoById(Integer id) {
        return ResultUtil.execOp(staffMapper.deleteByPrimaryKey(id), "删除");
    }

    /**
     * 根据id查询该人的部门、路长等级和姓名
     *
     * @param staffId
     * @return
     */
    @Override
    public ResultBean selectInfoByid(Integer staffId) {
        Staff staff = staffMapper.selectInfoByid(staffId);
        if (staff != null) {
            ResultUtil.setOK("success", staff);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 手机端登录
     *
     * @param user
     * @param password
     * @return
     */
    @Override
    public ResultBean appLogin(String user, String password, String IMEI, String sf) {

        //加密
        password = md5.MD5Encode(password, "utf-8");
        Staff staff = staffMapper.selectByLogin(user, password);
        Register register1 = new Register();
        Register register = registerMapper.selectByUserPss(user);
        if (staff != null) {
            if (register == null) {

                register1.setUsername(user);
                register1.setUserpass(password);
                register1.setImei(IMEI);
                register1.setFlag(sf);
                registerMapper.insertSelective(register1);
                return ResultUtil.setOK("success", staff);
            }
            if (register.getImei() != null || register.getImei() != "" || register1.getImei().equals(IMEI)) {
                if (register.getImei() == null) {
                    register1.setUsername(user);
                    register1.setUserpass(password);
                    register1.setImei(IMEI);
                    register1.setFlag(sf);
                    int i = registerMapper.updateRegisterByUser(IMEI, user);
                    if (i > 0) {
                        return ResultUtil.setOK("success", staff);
                    }

                    return ResultUtil.setError(SystemCon.RERROR1, "error", null);
                }

                if (register.getImei().equals(IMEI)) {
                    return ResultUtil.setOK("success", staff);
                } else {
                    return ResultUtil.setError(SystemCon.RERROR1, "账号已登录", register1.getImei());
                }

            }

        } else {
            return ResultUtil.setError(SystemCon.RERROR1, "error", null);
        }
        return null;
    }


    /**
     * 手机端APP登出
     *
     * @param IMEI
     * @param user
     * @return
     */
    public ResultBean appLoginOut(String IMEI, String user) {
        IMEI = null;
        return ResultUtil.execOp(registerMapper.updateRegisterByUser(IMEI, user), "登出");
    }

    /**
     * 修改唯一标识
     *
     * @param register
     * @return
     */
    @Override
    public ResultBean updateRegiser(Register register) {
        return ResultUtil.execOp(registerMapper.updateRegisterByUser(register.getImei(), register.getUsername()), "顶掉");
    }

    /**
     * 默认人
     *
     * @param probleDetail
     * @return
     */
    @Override
    public ResultBean selectdefault(String probleDetail) {
        List<Staff> selectdefault = staffMapper.selectdefault(probleDetail);
        if (selectdefault.size() > 0) {
            return ResultUtil.setOK("success", selectdefault);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 修改密码
     *
     * @param staff
     * @return
     */
    @Override
    public ResultBean modifuByPass(Staff staff) {
        //加密
        staff.setStaffPassword(md5.MD5Encode(staff.getStaffPassword(), "utf-8"));
        int i = staffMapper.updateByPrimaryKeySelective(staff);
        if (i > 0) {
            return ResultUtil.execOp(staffMapper.updateByPrimaryKeySelective(staff), "修改密码");
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 返回各大队
     *
     * @param staffid
     * @return
     */
    @Override
    public ResultBean selectStaffBySectionName(Integer staffid) {
        Staff staff = staffMapper.selectInfoByid(staffid);

        Integer sysSectionId = staff.getSysSectionId();
        Staff staff1 = staffMapper.selectStaffBySectionName(sysSectionId);
        if (staff1 != null) {
            return ResultUtil.setOK("success", staff1);
        }

        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }


    @Override
    public ResultBean selectStaffInfo() {
        return ResultUtil.setOK("success", staffMapper.selectStaffInfo());
    }

    /**
     * 查询在岗人数
     *
     * @return
     */
    @Override
    public ResultBean selectStaffByzg(Integer sectionId, Integer sectionPid, String sectionName) {
        List<Staff> staff = staffMapper.selectStaffByzg(sectionId, sectionPid, sectionName);
        if (staff.size() != 0) {
            for (int i = 0; i < staff.size(); i++) {
                Gps gps = gpsMapper.gpsEnd(staff.get(i).getSysStaffId());
                staff.get(i).setGps(gps);
            }
            return ResultUtil.setOK("sucess", staff);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    @Override
    public ResultBean selectStaffByName(String Name, String staffHierarchy) {
        List<Staff> staff = staffMapper.selectStaffByName(Name, staffHierarchy);
        if (staff.size() >= 0) {
            return ResultUtil.setOK("success", staff);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 根据职位显示在线人数
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean selectStaffInfoByid(Integer id) {
        Staff staff = staffMapper.selectInfoByid(id);
        if (staff == null) {
            return ResultUtil.setError(SystemCon.RERROR1, "error", null);
        }
        if (staff.getStaffPost().equals("管理员") || staff.getStaffPost().equals("支队领导")) {
            //应到人
            List<Staff> staffglyy = staffMapper.selectStaffYdByAll(null, null, null);
            //实到
            List<Staff> staffglys = staffMapper.selectStaffByzg(null, null, null);
            for (int i = 0; i < staffglyy.size(); i++) {
                for (int q = 0; q < staffglys.size(); q++) {
                    if (staffglyy.get(i).getSysStaffId() == staffglys.get(q).getSysStaffId()) {
                        staffglyy.get(i).setStaffOnline("1");
                        Gps gps = gpsMapper.gpsEnd(staffglyy.get(i).getSysStaffId());
                        staffglyy.get(i).setGps(gps);
                    }
                }
            }
            return ResultUtil.setOK("success", staffglyy);
        } else if (staff.getStaffPost().equals("大队长") || staff.getStaffPost().equals("副大队长")) {
            //应到人
            List<Staff> staffglyy = staffMapper.selectStaffYdByAll(null, staff.getSysSectionId(), null);
            //实到
            List<Staff> staffglys = staffMapper.selectStaffByzg(null, null, staff.getSectionName());
            for (int i = 0; i < staffglyy.size(); i++) {
                for (int q = 0; q < staffglys.size(); q++) {
                    if (staffglyy.get(i).getSysStaffId().equals(staffglys.get(q).getSysStaffId())) {
                        staffglyy.get(i).setStaffOnline("1");
                        Gps gps = gpsMapper.gpsEnd(staffglyy.get(i).getSysStaffId());
                        staffglyy.get(i).setGps(gps);
                    }
                }
            }
            return ResultUtil.setOK("success", staffglyy);
        } /*else if (staff.getStaffPost().equals("中队长")) {
            //应到人
            List<Staff> staffglyy = staffMapper.selectStaffYdByAll(null,null,staff.getSysSectionId());
            //实到
            List<Staff> staffglys = staffMapper.selectStaffByzg(staff.getSysSectionId(), null, null);
            for (int i = 0; i < staffglyy.size(); i++) {
                for (int q = 0; q < staffglys.size(); q++) {
                    if (staffglyy.get(i).getSysStaffId() == staffglys.get(q).getSysStaffId()) {
                        staffglyy.get(i).setStaffOnline("1");
                        System.out.println();
                        Gps gps = gpsMapper.gpsEnd(staffglyy.get(i).getSysStaffId());
                        staffglyy.get(i).setGps(gps);
                    }
                }
            }
            return ResultUtil.setOK("success", staffglyy);
        }*/
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }


    @Override
    public ResultBean selectStaffZx(Integer id) {
        Staff staff = staffMapper.selectInfoByid(id);
        if (staff == null) {
            return ResultUtil.setError(SystemCon.RERROR1, "error", null);
        }
        if (staff.getStaffPost().equals("管理员") || staff.getStaffPost().equals("支队领导")) {
            //实到
            List<Staff> staffglys = staffMapper.selectStaffByzg(null, null, null);
            for (int i = 0; i < staffglys.size(); i++) {

                staffglys.get(i).setStaffOnline("1");
                Gps gps = gpsMapper.gpsEnd(staffglys.get(i).getSysStaffId());
                staffglys.get(i).setGps(gps);
            }
            return ResultUtil.setOK("success", staffglys);
        } else if (staff.getStaffPost().equals("大队长") || staff.getStaffPost().equals("副大队长")) {
            //实到
            List<Staff> staffglys = staffMapper.selectStaffByzg(null, null, staff.getSectionName());
            for (int i = 0; i < staffglys.size(); i++) {
                staffglys.get(i).setStaffOnline("1");
                Gps gps = gpsMapper.gpsEnd(staffglys.get(i).getSysStaffId());
                staffglys.get(i).setGps(gps);
            }
            return ResultUtil.setOK("success", staffglys);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }


    /**
     * 查询所有的应到人实到人
     *
     * @param changeShifts
     * @return
     */
    @Override
    public ResultBean selectStaffYdByAll(String changeShifts, Integer SectionId) {
        //应到
        List<Staff> staff = staffMapper.selectStaffYdByAll(null, SectionId, null);
        //实到
        List<Staff> staff1 = staffMapper.selectpoliceZx();
        for (int i = 0; i < staff.size(); i++) {
            for (int k = 0; k < staff1.size(); k++) {
                if (staff.get(i).getSysStaffId().equals(staff1.get(k).getSysStaffId())) {
                    staff.get(i).setStaffOnline("1");
                    staff.get(i).setPatrolRecordBegintime(staff1.get(k).getPatrolRecordBegintime());
                }
            }
        }
        return ResultUtil.setOK("success", staff);
    }

    //在线
    @Override
    public ResultBean selectpoliceZx(String station) throws Exception {
        List<Staff> staff = staffMapper.selectpoliceZx();
        for (int i = 0; i < staff.size(); i++) {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String dayTime = df.format(new Date());// new Date()为获取当前系统时间

            Date startTime = ft.parse(dayTime + " 07:30:00");
            Date endTime = ft.parse(dayTime + " 08:30:00");
            Date startTime2 = ft.parse(dayTime + " 17:30:00");
            Date endTime2 = ft.parse(dayTime + " 18:30:00");
            Date startTime3 = ft.parse(dayTime + " 08:30:00");
            Date endTime3 = ft.parse(dayTime + " 17:30:00");
            Date nowTime = new Date();
            //早高峰
            boolean effectiveDate = DataTime.hour(nowTime, startTime, endTime);
            //晚高峰
            boolean effectiveDate2 = DataTime.hour(nowTime, startTime2, endTime2);
            //固定岗 平峰期
            boolean effectiveDate3 = DataTime.hour(nowTime, startTime3, endTime3);

            //根据人查出来的岗位
            List<Xarea> xareas = xareaMapper.selectXareaZgByStaffId(staff.get(i).getSysStaffId(),station);
            for (int f = 0; f < xareas.size(); f++) {
                staff.get(i).setXarea(xareas.get(f));
                    /*if (effectiveDate==true && xareas.get(f).getStation().equals("高峰岗")) {
                        System.out.println("111111111111");

                    } else if (effectiveDate2==true && xareas.get(f).getStation().equals("高峰岗")) {
                        System.out.println("222222222222222222");
                        staff.get(i).setXarea(xareas.get(f));
                    } else if (effectiveDate3==true && xareas.get(f).getStation().equals("固定岗")) {
                        System.out.println("3333333333333333333");
                        staff.get(i).setXareaList(xareas);
                    } else {
                        System.out.println("44444444444444444444");
                        staff.get(i).setXareaList(null);
                    }*/

            }
            Gps gps = gpsMapper.gpsEnd(staff.get(i).getSysStaffId());
            staff.get(i).setStaffOnline("1");
            staff.get(i).setGps(gps);
        }
        return ResultUtil.setOK("success", staff);
    }

    //根据id查询
    @Override
    public ResultBean selectStaffByid(Integer id) {
        Staff staff = staffMapper.selectStaffBypid(id);
        if (staff != null) {
            return ResultUtil.setOK("success", staff);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    /**
     * 按id查询民警应巡查路段
     *
     * @param id
     * @return
     */
    @Override
    public ResultBean selectStaffXareaByid(Integer id) throws Exception {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String dayTime = df.format(new Date());// new Date()为获取当前系统时间

        Date startTime = ft.parse(dayTime + " 07:30:00");
        Date endTime = ft.parse(dayTime + " 09:00:00");
        Date startTime2 = ft.parse(dayTime + " 17:30:00");
        Date endTime2 = ft.parse(dayTime + " 18:30:00");
        Date startTime3 = ft.parse(dayTime + " 07:00:00");
        Date endTime3 = ft.parse(dayTime + " 20:00:00");
        Date nowTime = new Date();
        //早高峰
        boolean effectiveDate = DataTime.hour(nowTime, startTime, endTime);
        //晚高峰
        boolean effectiveDate2 = DataTime.hour(nowTime, startTime2, endTime2);
        //固定岗
        boolean effectiveDate3 = DataTime.hour(nowTime, startTime3, endTime3);
        //根据人查出来的岗位
        Staff staff = staffMapper.selectInfoByid(id);
        List<Xarea> xareas = xareaMapper.selectXareaZgByStaffId(id,null);
        List<Xarea> xareas1 = new ArrayList<>();
        //计算民警的上班时长跟巡查轨迹
        Patrolrecord patrolrecords = patrolrecordMapper.selectrecordByid(id);
        if (patrolrecords != null) {
            String patrolRecordGps = patrolrecords.getPatrolRecordGps();
            Date date = new Date();
           /* String start=df.format(date);
            String end=df.format(patrolrecords.getPatrolRecordBegintime());*/
            //上班时长
            String duration = DataTime.getDatePoor(date, patrolrecords.getPatrolRecordBegintime());

            DecimalFormat dub = new DecimalFormat("######0.00");
            //切割GPS点
            String[] split = patrolRecordGps.split(",");
           /* Double num=0.0;
            for (int g=0;g<split.length;g++){
                if(g+1!=split.length){
                    double v = Double.parseDouble(split[g].split(" ")[0]);
                    double v1 = Double.parseDouble(split[g].split(" ")[1]);
                    double s =  Double.parseDouble(split[g+1].split(" ")[0]);
                    double s1 =  Double.parseDouble(split[g+1].split(" ")[1]);
                    System.out.println(v);
                    System.out.println(v1);
                    System.out.println(s);
                    System.out.println(s1);
                    double distance = DataTime.getDistance(v,v1,s,s1);
                    System.out.println("asd====:"+distance);
                    num+=distance;
                }
            }
            //第一个GPS点
            String[] s = split[0].split(" ");
            Double startgp1=Double.parseDouble(s[0]);
            Double startgp2=Double.parseDouble(s[1]);
            //第二个GPS点
            String[] s1 = split[split.length-1].split(" ");
            Double endgp1=Double.parseDouble(s1[0]);
            Double endgp2=Double.parseDouble(s1[1]);*/
            int num = split.length * 10;
            //巡查距离
            staff.setDuration(duration);
            //num = (double) Math.round(num * 100) / 100;
            staff.setJuli(num);
            staff.setPatrolrecord(patrolrecords);
        }


        for (int f = 0; f < xareas.size(); f++) {
            if (effectiveDate && xareas.get(f).getStation().equals("高峰岗")) {
                xareas1.add(xareas.get(f));
                staff.setXareaList(xareas1);
            } else if (effectiveDate2 && xareas.get(f).getStation().equals("高峰岗")) {
                xareas1.add(xareas.get(f));
                staff.setXareaList(xareas1);
            } else if (effectiveDate3 && xareas.get(f).getStation().equals("固定岗")) {
                staff.setXareaList(xareas);
            }

        }
        //staff.setXareaList(xareas);
        Gps gps = gpsMapper.gpsEnd(staff.getSysStaffId());
        staff.setGps(gps);
        return ResultUtil.setOK("success", staff);
    }

    /**
     * 查询昨日在岗警力人数
     *
     * @return
     */
    @Override
    public ResultBean selecttotalforces() {
        Integer selecttotalforces = staffMapper.selecttotalforces();
        if (selecttotalforces != null || selecttotalforces != null) {
            return ResultUtil.setOK("success", staffMapper.selecttotalforces());
        }
        return ResultUtil.setError(SystemCon.RERROR1, "error", null);
    }

    @Override
    public ResultBean selecttotalforceszr() {
        List<HashMap> selecttotalforceszr = staffMapper.selecttotalforceszr();
        if (selecttotalforceszr.size() >= 0) {
            for (int i = 0; i < selecttotalforceszr.size(); i++) {
                String sectionName = selecttotalforceszr.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = selecttotalforceszr.get(i).get("sectionName").toString().substring(0, 3);
                    selecttotalforceszr.get(i).put("sectionName", sectionName1);
                }
            }

            return ResultUtil.setOK("success", selecttotalforceszr);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "success", null);
    }

    @Override
    public ResultBean selectzaBystation(String station,String conment,String grid) {
        List<Staff> staff = staffMapper.selectzaBystation(station, conment, grid);
        for (int i=0;i<staff.size();i++){
            Gps gps = gpsMapper.gpsEnd(staff.get(i).getSysStaffId());
            staff.get(i).setGps(gps);
        }
        return ResultUtil.setOK("success",staff);
    }

    @Override
    public ResultBean selectStaffByqita() {
        List<Staff> staff = staffMapper.selectStaffByqita();
        for (int i=0;i<staff.size();i++){
            Gps gps = gpsMapper.gpsEnd(staff.get(i).getSysStaffId());
            staff.get(i).setGps(gps);
        }
        return ResultUtil.setOK("success",staff);
    }

    /**
     * 以单位为结构显示信息列表
     *
     * @return
     */
    @Override
    public ResultBean selectAllbytype(String battalion) {

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

        //实到在线人数
        List<Staff> gdorGfSDsum = staffMapper.countGdorGfSDsum("固定岗", battalion);
        List<Staff> staff = staffMapper.countGdorGfSDsumtj();
        List<Staff> tqSD = staffMapper.countTqSDsum(battalion);
        List<Staff> wgSD = staffMapper.countWgSDsum(battalion);
        List<Staff> zdSD = staffMapper.countZdSDsum(battalion);//重点
        List<Staff> qtSD = staffMapper.countQtSDsum(battalion);
        List<Staff> gsSD = staffMapper.countGsorKsSDsum("高速岗", battalion, null);

        gdMap.put("固定岗",staff);
        gdMap.put("pepole",gdorGfSDsum);


        typeSumList.add(gdMap);
        return ResultUtil.setOK("success",typeSumList);
    }

    @Override
    //根据岗位查询各大队在线民警
    public ResultBean selectcountBysection(String station){

        List<HashMap> selectcountBysection = staffMapper.selectcountBysection(station);
         if (selectcountBysection.size() >= 0) {
           for (int i = 0; i < selectcountBysection.size(); i++) {
                String sectionName = selectcountBysection.get(i).get("sectionName").toString();
                if (sectionName.indexOf("大队") != -1) {
                    String sectionName1 = selectcountBysection.get(i).get("sectionName").toString().substring(0, 3);
                    selectcountBysection.get(i).put("sectionName", sectionName1);
                }
            }

            return ResultUtil.setOK("success", selectcountBysection);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "success", selectcountBysection);
    }

    @Override
    //根据岗位查询各大队在线民警详情
    public ResultBean selectAllBysection(String station, String sectionName){
        List<HashMap> selectAllBysection = staffMapper.selectAllBysection(station,sectionName);
        if (selectAllBysection.size() >= 0) {
            return ResultUtil.setOK("success", selectAllBysection);
        }
        return ResultUtil.setError(SystemCon.RERROR1, "success", null);
    }

}
