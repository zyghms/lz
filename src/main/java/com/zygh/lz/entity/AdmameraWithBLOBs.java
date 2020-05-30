package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmameraWithBLOBs extends Admamera  implements Serializable{
    private String ossurl;

    private String shortVideoCode;

}