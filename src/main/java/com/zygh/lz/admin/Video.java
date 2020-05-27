package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Serializable {
    private Integer id;

    private String basicid;

    private String computerip;

    private String host;

    private String basicip;

    private String accout;

    private String password;

}