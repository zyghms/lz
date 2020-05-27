package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Urban implements Serializable{
    private Integer sysUrbanId;

    private Integer sysSectionId;

    private String urbanName;

    private String sectionName;         //部门名称

}