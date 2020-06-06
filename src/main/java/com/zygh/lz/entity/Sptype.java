package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * User:luanhuajuan
 * Date:2020-06-02 17:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sptype implements Serializable {
    private Integer id;

    private String ssname;

    private Integer lx;  //对应等级勤务的state字段

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cjsj;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zhxgsj;

    private Integer yxzt;


}
