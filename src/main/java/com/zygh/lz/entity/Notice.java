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
public class Notice implements Serializable {
    private Integer sysNoticeId;

    private String noticeContent;

    private String noticeUrl;

    private Integer noticeSend;

    private String noticeAccept;

    private String noticeTitle;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeTime;

    private String noticeDetail;

    private String staffName;

    private String noticeSectionName;
}