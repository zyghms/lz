package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register implements Serializable{
    private Integer id;

    private String username;

    private String userpass;

    private String imei;

    private String flag;

}