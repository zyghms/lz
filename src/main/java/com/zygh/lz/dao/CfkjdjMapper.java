package com.zygh.lz.dao;

import com.zygh.lz.entity.Cfkjdj;
import com.zygh.lz.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CfkjdjMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cfkjdj record);

    int insertSelective(Cfkjdj record);

    Cfkjdj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cfkjdj record);

    int updateByPrimaryKey(Cfkjdj record);

    //未上线人
    List<String> findStaffBywsx();

    //上线为签退
    List<String> findCfkjdjbywqt();

    Cfkjdj findByUserid(String userid);

    //按岗位统计实到人数
    Integer findByrwmc(@Param("rwmc") String rwmc, @Param("rwzmc") String rwzmc);

    List<String> selectAllStaffInfo();

    //判断当天数据库是否存在该警员的签到信息
    List<String> selectqdBystaff(@Param("staffnum") String staffnum ,@Param("zxzt") String zxzt);
}