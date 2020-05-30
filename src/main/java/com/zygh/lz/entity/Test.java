package com.zygh.lz.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test implements Serializable{
    private Integer id;

    private String name;

    private String gps;

    private String po;
}