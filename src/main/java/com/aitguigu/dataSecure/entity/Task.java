package com.aitguigu.dataSecure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author LucyChen
 * @date 2024-2-4
 * @desc:
 */
@Entity(name="task")
@Data
public class Task {
    @Id
    private String id;
    private String taskName;
    private String type;
    private String project;
    private Integer used;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp updateTime;
    private String weiban;
    private String database;
}
