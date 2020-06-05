package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 对接长峰科技新增任务组实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangFeng implements Serializable {
    private String cjrdw;
    private String pid;
    private String px;
    private String rwzmc;
    private String rwzms;


}
