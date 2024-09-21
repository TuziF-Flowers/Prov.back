package com.aitguigu.dataSecure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name="work_fact")
@Data
public class Work_fact {
    @Id
    private String XID;
    private Timestamp XCREATETIME;
    private Timestamp XUPDATETIME;
    private String XARRIVEDACTIVITY;
    private String XARRIVEDACTIVITYTOKEN;
    private Timestamp XARRIVEDTIME;
    private String XCOMPLETED;
    private String XCONNECTED;
    private String XDURATION;
    private String XFROMACTIVITY;
    private String XFROMACTIVITYTOKEN;
    private Timestamp XFROMTIME;
    private String XJOB;
    private String XPROCESS;
    private String XPROCESSALIAS;
    private String XPROCESSNAME;
    private String XPROPERTIES;
    private String XROUTE;
    private String XROUTENAME;
    private String XSPLITTOKEN;
    private String XSPLITVALUE;
}
