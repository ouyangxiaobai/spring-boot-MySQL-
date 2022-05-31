package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *仪器设备：(InstrumentsAndEquipment)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "InstrumentsAndEquipment")
public class InstrumentsAndEquipment implements Serializable {

    //InstrumentsAndEquipment编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instruments_and_equipment_id")
    private Integer instruments_and_equipment_id;
   // 仪器编号
   @Basic
    private String instrument_no;
   // 仪器名称
   @Basic
    private String instrument_name;
   // 仪器类别
   @Basic
    private String instrument_category;
   // 购入日期
   @Basic
    private Timestamp purchase_date;
   // 照片
   @Basic
    private String photo;
   // 状态
   @Basic
    private String state;
   // 注意事项
   @Basic
    private String matters_needing_attention;
   // 仪器介绍
   @Basic
    private String instrument_introduction;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
