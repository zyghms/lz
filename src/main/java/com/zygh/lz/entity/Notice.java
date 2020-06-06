package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeTime;

    private String noticeDetail;

    private String staffName;

    private String noticeSectionName;
}