package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problemtype implements Serializable {
    private Integer id;

    private String types;

    private String department;

    private String parents;
}