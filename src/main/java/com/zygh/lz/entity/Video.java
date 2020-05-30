package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Serializable {
    public Integer id;

    public String basicid;

    public String computerip;

    public String host;

    public String basicip;

    public String accout;

    public String password;

}