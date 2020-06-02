package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dailyservice implements Serializable {
    private int id;
    private String staffnum;
    private String staffname;
    private String phone;
    private String signintime;
    private String signouttime;
    private String duration;
    private String Patrollength;

}
