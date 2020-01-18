package com.zygh.lz.mapper;

import com.zygh.lz.admin.Staff;
import com.zygh.lz.admin.Xarea;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.util.HashMap;
import java.util.List;

public interface XareaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Xarea record);

    int insertSelective(Xarea record);

    Xarea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Xarea record);

    int updateByPrimaryKeyWithBLOBs(Xarea record);

    int updateByPrimaryKey(Xarea record);

    //根据大队，中队，岗位，中队领导
    List<Xarea> selectXareabycondition(@Param("battalion") String battalion,
                                       @Param("detachment") String detachment,
                                       @Param("station") String station,
                                       @Param("leader") String leader,
                                       @Param("grid") String grid,
                                       @Param("type") String type);

    //根据名字模糊查询
    List<Xarea> selectXareaByName(String Name);

    //查询所有区域
    List<Xarea> selectXareaAll();

    List<Xarea> selectXareaZgByStaffId(@Param("id") Integer id,@Param("station") String station);

    List<HashMap> countYDSum(@Param("station") String station, @Param("battalion") String battalion);

    List<HashMap> countYxSum(@Param("conment") String conment, @Param("battalion") String battalion);

    List<HashMap> countRcYDsumC2(String battalion);
    List<HashMap> countRcYDsum(String battalion);

    List<Integer> countWgSum(String battalion);

    List<Integer> countZdYDSum(String battalion);

    List<Integer> countQtYDSum(@Param("battalion") String battalion,@Param("conment") String conment);

    List<Integer> countTqZSum(String battalion);

    int countggZSum(@Param("station") String station, @Param("battalion") String battalion);

    int countWgZSum(String battalion);

    int countZdZSum(String battalion);

    int countQtZSum(@Param("battalion")String battalion,@Param("conment")String conment);

    List<Integer> countZDRc(@Param("battalion") String battalion, @Param("detachment") String detachment, @Param("station") String station);
    List<Integer> countZDRcC2(@Param("battalion") String battalion, @Param("detachment") String detachment);

    List<HashMap> countZDYxorTq(@Param("conment") String conment, @Param("battalion") String battalion, @Param("detachment") String detachment);

    List<String> findDd();

    List<String> findZd(@Param("battalion") String battaliont, @Param("conment") String conmen);

    List<Integer> countGsOrKsZ(@Param("station") String station, @Param("battalion") String battalion);

    List<Integer> countTQZ(String battalion);

    List<Integer> countGsOrKsYDSum(@Param("station") String station, @Param("battalion") String battalion, @Param("conment") String conmen);

    //查询夜间快速大队警组，警员
    List<Xarea> selectks();

    //夜巡组数
    List<Integer> countYxZ(String battalion);
    //夜巡应到
    List<Integer> countYxYDsum(String battalion);

    //查询其他
    List<Xarea> selectqt(String conment);

}