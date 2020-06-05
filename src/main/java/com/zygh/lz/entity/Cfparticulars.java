package com.zygh.lz.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 对接长峰科技新增任务详情实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cfparticulars implements Serializable {

    private String cjr;                 //创建人
    private String cjrdw;               //创建人所属单位  默认给诗句机构代码
    private String rwid;                //创建任务时此字段要为空，修改是填写要修改任务的对应id
    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String jssj;                  // 结束时间
   // @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String kssj;                  // 开始时间
    private String rwmc;                 // 任务名称
    private String rwms;                // 任务明细
    private String rwzid;               // 所属任务组id
    //@JSONField(format="HH:mm:ss")
    private String sgsj;                  // 上岗时间  格式[HH:mm:ss]
    private String[] typeZB;            // 警员佩戴的装备
    private String[] userIds;           // 关联的警员String类型的数据传送
    //@JSONField(format="HH:mm:ss")
    private String xgsj;                   // 下岗时间  格式[HH:mm:ss]
    private String ydrs;                   //应到人数
}
