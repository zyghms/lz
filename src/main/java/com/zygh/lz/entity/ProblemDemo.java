package com.zygh.lz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 公司：河南中裕广恒科技股份有限公司
 * 项目：知识库管理系统
 * 编程人员：研发部柯博
 * 时间：2018/10/26 14:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDemo implements Serializable {
    private Integer count;              //数量
    private String sectionName;         //部门名称
    private String problemResove;       //问题是否解决

}
