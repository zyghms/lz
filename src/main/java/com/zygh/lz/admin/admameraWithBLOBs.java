package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class admameraWithBLOBs extends admamera  implements Serializable{
    private String ossurl;

    private String shortVideoCode;

}