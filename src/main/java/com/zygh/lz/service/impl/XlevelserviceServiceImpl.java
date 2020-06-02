package com.zygh.lz.service.impl;

import com.zygh.lz.dao.SectionMapper;
import com.zygh.lz.dao.XareaMapper;
import com.zygh.lz.entity.Section;
import com.zygh.lz.entity.Xlevelservice;
import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.dao.XlevelserviceMapper;
import com.zygh.lz.service.XlevelserviceService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class XlevelserviceServiceImpl implements XlevelserviceService {
    @Autowired
    private XlevelserviceMapper xlevelserviceMapper;
    @Autowired
    private SectionMapper sectionMapper;

    @Autowired
    private XareaMapper xareaMapper;


    /**
     * 查询全部等级勤务
     *
     * @return
     */
    @Override
    public ResultBean selectorderlyAll(String sectionName) {
        int success = xlevelserviceMapper.selectorderlyAll(sectionName);
        return ResultUtil.setOK("success", success);
    }

    /**
     * 等级勤务
     *
     * @return
     */
    @Override
    public ResultBean selectorderlydjyd(String sectionName) {
        List<Map> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        //一级应到
        List<HashMap> selectorderlyoneyd = xlevelserviceMapper.selectorderlyoneyd(sectionName);
        Integer selectyjyd = xlevelserviceMapper.selectyjyd(sectionName);
        if (selectyjyd == null) {
            selectyjyd = 0;
        }
        for (int i = 0; i < selectorderlyoneyd.size(); i++) {
            selectorderlyoneyd.get(i).put("SDnum", 0);
            selectorderlyoneyd.get(i).put("gfZXL", 0);
        }
        //二级应到
        List<HashMap> selectorderlytweyd = xlevelserviceMapper.selectorderlytweyd(sectionName);
        Integer selectejyd = xlevelserviceMapper.selectejyd(sectionName);
        if (selectejyd == null) {
            selectejyd = 0;
        }
        for (int i = 0; i < selectorderlytweyd.size(); i++) {
            selectorderlytweyd.get(i).put("SDnum", 0);
            selectorderlytweyd.get(i).put("gfZXL", 0);
        }
        //三级应到
        List<HashMap> selectorderlythreeyd = xlevelserviceMapper.selectorderlythreeyd(sectionName);
        Integer selectsjyd = xlevelserviceMapper.selectsjyd(sectionName);
        if (selectsjyd == null) {
            selectsjyd = 0;
        }
        for (int i = 0; i < selectorderlythreeyd.size(); i++) {
            selectorderlythreeyd.get(i).put("SDnum", 0);
            selectorderlythreeyd.get(i).put("gfZXL", 0);
        }

        map1.put("ddName", "一级勤务");
        map1.put("ddSDnum", 0);
        map1.put("ddYDnum", selectyjyd);
        map1.put("gfZXL", 0);
        map1.put("zdCount", selectorderlyoneyd);

        map2.put("ddName", "二级勤务");
        map2.put("ddSDnum", 0);
        map2.put("ddYDnum", selectejyd);
        map2.put("gfZXL", 0);
        map2.put("zdCount", selectorderlytweyd);

        map3.put("ddName", "三级勤务");
        map3.put("ddSDnum", 0);
        map3.put("ddYDnum", selectsjyd);
        map3.put("gfZXL", 0);
        map3.put("zdCount", selectorderlythreeyd);

        list.add(map1);
        list.add(map2);
        list.add(map3);
        /*map.put("stair",selectorderlyoneyd);
        map.put("second",selectorderlytweyd);
        map.put("three",selectorderlythreeyd);*/
        return ResultUtil.setOK("success", list);
    }

    @Override
    public ResultBean selectxleveBydj(Integer hierarchy, String sectionName) {
        List<Xlevelservice> xlevelservices = xlevelserviceMapper.selectxleveBydj(hierarchy, sectionName);
        if (xlevelservices.size() >= 0) {
            return ResultUtil.setOK("success", xlevelservices);
        }
        return null;
    }

    /**
     * 等级勤务按等级按大队区分
     *
     * @param hierarchy
     * @return
     */
    @Override
    public ResultBean selectXlevedJ(Integer hierarchy) {
        List<HashMap> xlevelservices = xlevelserviceMapper.selectXlevedJ(hierarchy);
        if(xlevelservices.size()>0){
            for (int i = 0; i < xlevelservices.size(); i++) {
                List<Xlevelservice> xlevelservices1 = xlevelserviceMapper.selectXleverdJnum(hierarchy,xlevelservices.get(i).get("sectionName").toString());
                xlevelservices.get(i).put("detachment",xlevelservices1);
            }
            return ResultUtil.setOK("success",xlevelservices);
        }
        return ResultUtil.setError(SystemCon.RERROR1,"error",null);
    }

    @Override
    public ResultBean selectSpecialService() {
        List<Xlevelservice> listAll=new ArrayList<> ();
        //父级菜单集合
        List<Xlevelservice> sectionList = new ArrayList<Xlevelservice>();
        //三级菜单集合
        List<Xlevelservice> selectBySublevel = new ArrayList<Xlevelservice>();

        List<Xlevelservice> levelMenu = new ArrayList<Xlevelservice>();

        List<Xlevelservice> testList = new ArrayList<Xlevelservice>();

        List<Section> sections = sectionMapper.selectAllSection();
        for (int i = 0; i < sections.size(); i++) {
            if(sections.get(i).getSectionPid()==0){
                Xlevelservice xlevel = new Xlevelservice();
                xlevel.setId(sections.get(i).getSysSectionId());
                xlevel.setNumber(sections.get(i).getSectionPid());
                xlevel.setCallsign(sections.get(i).getSectionName());
                xlevel.setPlace(sections.get(i).getSectionPosition());
                xlevel.setLocation(sections.get(i).getSectionTel());
                xlevel.setHierarchy(sections.get(i).getSectionPerson());
                sectionList.add(xlevel);
            }
        }

        //子级大队菜单 后期需要修改下
        Iterator iterList = sectionList.iterator();
        while (iterList.hasNext()) {
            Xlevelservice treemenu = (Xlevelservice) iterList.next();
            Xlevelservice xlevels = new Xlevelservice();
            xlevels.setId(1);
            xlevels.setNumber(2);
            xlevels.setCallsign("等级勤务");
            selectBySublevel.add(xlevels);
            treemenu.setSectionList(selectBySublevel);
        }
        //三级菜单集合
        Iterator<Xlevelservice> iterator = selectBySublevel.iterator();
        while (iterator.hasNext()){
            Xlevelservice next = iterator.next();
            levelMenu =  xlevelserviceMapper.selectSpecialService();
            next.setSectionList(levelMenu);
        }

        //子级大队菜单
//        Iterator iterList = sectionList.iterator();
//        while (iterList.hasNext()) {
//            Xlevelservice treemenu = (Xlevelservice) iterList.next();
//            selectBySublevel =  xlevelserviceMapper.selectSpecialService();
//            treemenu.setSectionList(selectBySublevel);
//        }

        return ResultUtil.setOK("success",sectionList);

    }


    @Override
    public ResultBean delSpecialService(Integer id) {
        return ResultUtil.execOp(xlevelserviceMapper.delSpecialService(id),"删除");


    }

    @Override
    public ResultBean addSpecialService(Xlevelservice xlevelservice) {
        return ResultUtil.execOp(xlevelserviceMapper.insert(xlevelservice),"新增");

    }

    @Override
    public ResultBean updateSpecialService(Xlevelservice  xlevelservice) {
        return ResultUtil.execOp(xlevelserviceMapper.updateByPrimaryKeySelective(xlevelservice),"修改");


    }

}
