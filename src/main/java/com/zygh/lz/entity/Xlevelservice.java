package com.zygh.lz.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Xlevelservice implements Serializable{
    private Integer id;

    private String callsign;

    private String place;

    private Integer subofficeId;

    private Integer number;

    private Integer sectionId;

    private String ondutytime;

    private String location;

    private String hierarchy;

    private String conment;

}