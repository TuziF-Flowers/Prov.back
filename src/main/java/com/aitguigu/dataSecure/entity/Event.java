package com.aitguigu.dataSecure.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.sql.Timestamp;

/**
 * @author LucyChen
 * @date 2024-6-10
 * @desc:
 */
@Data
@NodeEntity("Event")
public class Event {
    @Id
    @GeneratedValue
    private Long identity;

    @Property("jobid")
    private String jobid;

    @Property("typeid")
    private String typeid;

    @Property("tasktype")
    private String tasktype;

    @Property("time")
    private String time;

    @Property("type")
    private String type;
}
