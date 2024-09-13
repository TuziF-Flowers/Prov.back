package com.aitguigu.dataSecure.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author LucyChen
 * @date 2024-2-4
 * @desc:
 */
@Entity(name="action")
@Data
public class Action {
    @Id
    private String id;
    private String threadId;
    private String job;
    private String type;
    private String databaseName;
    private String tableName;
    private String command;

    private String argument;
    private String status;
    private Timestamp createTime;
}
