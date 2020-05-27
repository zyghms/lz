package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Xrelation implements Serializable{
    private Integer id;

    private Integer staffId;

    private Integer xareaId;

    private String conmnet;

    private Integer pcty;
}