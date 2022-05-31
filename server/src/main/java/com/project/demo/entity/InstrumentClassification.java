package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *仪器分类：(InstrumentClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "InstrumentClassification")
public class InstrumentClassification implements Serializable {

    //InstrumentClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrument_classification_id")
    private Integer instrument_classification_id;
   // 仪器类别
   @Basic
    private String instrument_category;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
