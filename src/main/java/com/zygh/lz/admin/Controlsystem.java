package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Controlsystem {
    private Integer id;

    private String ip;

    private String systemname;

    private String conment;
}