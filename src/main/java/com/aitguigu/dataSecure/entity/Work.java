package com.aitguigu.dataSecure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name="work")
@Data
public class Work {
    @Id
    private String XJOB;
    private String XCOMPLETED;
    private String XSPLITTING;
    private String XAPPLICATION;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Timestamp XCREATETIME;
}
