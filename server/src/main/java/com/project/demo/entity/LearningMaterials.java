package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学习资料：(LearningMaterials)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LearningMaterials")
public class LearningMaterials implements Serializable {

    //LearningMaterials编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learning_materials_id")
    private Integer learning_materials_id;
   // 资料编号
   @Basic
    private String data_no;
   // 资料名称
   @Basic
    private String data_name;
   // 资料类型
   @Basic
    private String data_type;
   // 封面
   @Basic
    private String cover;
   // 资料
   @Basic
    private String data;
   // 学习视频
   @Basic
    private String learning_video;
   // 资料介绍
   @Basic
    private String information_introduction;
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
