package com.aitguigu.dataSecure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author LucyChen
 * @date 2024-2-15
 * @desc:
 */
@Entity(name="job")
@Data
public class Job {
    @Id
    private String id;
    private String type;
    private String nodeId;
    private String status;
    private String bizdate;
    @JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss", timezone="UTC")
    private Timestamp createTime;
    @JsonFormat(pattern="yyyy-MM-dd' 'HH:mm:ss", timezone="UTC")
    private Timestamp updateTime;
}
