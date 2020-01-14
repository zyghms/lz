package com.zygh.lz.mapper;

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

    List<Xarea> selectXareaZgByStaffId(Integer id);

    public List<HashMap> countYDSum(@Param("station")String station, @Param("battalion")String battalion);
    public List<HashMap> countYxSum(@Param("conment")String conment,@Param("battalion")String battalion);
    public List<HashMap> countRcYDsum(String battalion);
    public List<Integer> countWgSum(String battalion);
    public List<Integer> countZdYDSum(String battalion);
    public List<Integer> countQtYDSum(String battalion);

    public List<Integer> countTqZSum(String battalion);
    public int countggZSum(@Param("station")String station,@Param("battalion")String battalion);
    public int countWgZSum(String battalion);
    public int countZdZSum(String battalion);
    public int countQtZSum(String battalion);

    public List<Integer> countZDRc(@Param("battalion")String battalion,@Param("detachment")String detachment,@Param("station")String station);
    public List<HashMap> countZDYxorTq(@Param("conment")String conment,@Param("battalion")String battalion,@Param("detachment")String detachment);
    public List<String> findDd();
    public List<String> findZd(@Param("battalion")String battalion,@Param("conment")String conment);
}