package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Controlsubsystem implements Serializable {
    private Integer id;

    private String ip;

    private String subsystemname;

    private Integer systemid;

    private String conment;
    
}