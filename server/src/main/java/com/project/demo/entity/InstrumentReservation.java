package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *仪器预约：(InstrumentReservation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "InstrumentReservation")
public class InstrumentReservation implements Serializable {

    //InstrumentReservation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrument_reservation_id")
    private Integer instrument_reservation_id;
   // 仪器编号
   @Basic
    private String instrument_no;
   // 仪器名称
   @Basic
    private String instrument_name;
   // 仪器类别
   @Basic
    private String instrument_category;
   // 用户名
   @Basic
    private Integer user_name;
   // 联系电话
   @Basic
    private String contact_number;
   // 预约使用日期
   @Basic
    private Timestamp appointment_date;
   // 预约用途
   @Basic
    private String reservation_purpose;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
