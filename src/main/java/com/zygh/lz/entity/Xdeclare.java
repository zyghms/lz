package com.zygh.lz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date establishtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stoptime;

    private String state;

    private String conmnet;
}