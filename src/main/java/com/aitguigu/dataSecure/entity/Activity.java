package com.aitguigu.dataSecure.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name="activity")
@Data
@NoArgsConstructor
public class Activity {
    @Id
    private String ACTIVITY;
    private String ACTIVITYALIAS;
    private String ACTIVITYNAME;
    private String ACTIVITYTYPE;
    private String XAPPLICATION;
//    @ManyToOne
//    @JoinColumn(name = "XAPPLICATION", referencedColumnName = "XAPPLICATION")
//    private Application application;


}
