package com.aitguigu.dataSecure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author LucyChen
 * @date 2024-3-24
 * @desc:
 */
@Data
public class Column {
    String id;
    String nodeId;
    String taskName;
    String prgType;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    Timestamp startTime;
    String status;
    String totalTime;
    String table;
    String recordCnt;
    String size;
    String weiban;
    String user;
    String device;
}
