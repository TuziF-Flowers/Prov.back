package com.aitguigu.dataSecure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="time")
@Data
public class Time {
    @Id
    private Integer id;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;

}
