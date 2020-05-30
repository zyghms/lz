package com.zygh.lz.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Section implements Serializable {
    private Integer sysSectionId;

    private Integer sectionPid;

    private String sectionName;

    private String sectionPosition;

    private String sectionTel;

    private String sectionPerson;

    private List<Section> sectionList ;

}