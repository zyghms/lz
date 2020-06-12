package com.zygh.lz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
<<<<<<< HEAD
=======
import java.util.Date;
>>>>>>> 92fe566a105ed3087d448b805094612d7cd1daf4

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