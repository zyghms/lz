package com.zygh.lz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallData implements Serializable{
    private int id;

    private String name;

    private String data;

    private String result;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String cjsj;

    private String remark;


}