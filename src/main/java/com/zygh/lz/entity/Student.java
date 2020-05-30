package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    /**
     * 例子JavaBean
     * @author liuyazhuang
     *
     */
        private int id;
        private String name;
        private String sex;
}
