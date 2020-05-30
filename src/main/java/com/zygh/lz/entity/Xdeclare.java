package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private Date establishtime;

    private Date starttime;

    private Date stoptime;

    private String state;

    private String conmnet;
}