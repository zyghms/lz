package com.zygh.lz.dao;

import com.zygh.lz.entity.Cfkjdj;
<<<<<<< HEAD
import com.zygh.lz.entity.Staff;
=======
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
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

<<<<<<< HEAD
    List<String> selectAllStaffInfo();

    //判断当天数据库是否存在该警员的签到信息
    List<String> selectqdBystaff(@Param("staffnum") String staffnum ,@Param("zxzt") String zxzt);
=======
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4
}