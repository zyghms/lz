package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class message implements Serializable{
    private Integer sysMessageId;

    private Integer messagePid;

    private String messageType;

    private String messageName;

    private Integer messageState;

    private Integer acceptId;

    private Integer launchId;

    private Date createTime;

    private Integer isDelete;
}