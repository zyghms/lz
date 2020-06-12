package com.zygh.lz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable{
    private Integer sysMessageId;

    private Integer messagePid;

    private String messageType;

    private String messageName;

    private Integer messageState;

    private Integer acceptId;

    private Integer launchId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer isDelete;
}