package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Xdeclare implements Serializable {
    private Integer id;

    private String appear;

    private String accepter;

    private String document;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date establishtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stoptime;

    private String state;

    private String conmnet;
}