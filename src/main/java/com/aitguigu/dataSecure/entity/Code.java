package com.aitguigu.dataSecure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author LucyChen
 * @date 2024-3-22
 * @desc:
 */
@Entity(name="code")
@Data
public class Code {
    @Id
    private String nodeId;
    private String code;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="UTC")
    private Timestamp rksj;
    private String name1;
    private String steptype1;
    private String table1;
    private String name2;
    private String datasource1;
    private String steptype2;
    private String table2;
    private String datasource2;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="UTC")
    private Timestamp createTime;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss", timezone="UTC")
    private Timestamp updateTime;
}

