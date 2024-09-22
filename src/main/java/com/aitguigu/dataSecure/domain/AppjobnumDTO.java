package com.aitguigu.dataSecure.domain;

import java.math.BigInteger;

public class AppjobnumDTO {
    private String XAPPLICATION;
    private String XAPPLICATIONNAME;
    private BigInteger jobnum;
    public AppjobnumDTO(String XAPPLICATION,String XAPPLICATIONNAME,BigInteger  jobnum){
        this.XAPPLICATION=XAPPLICATION;
        this.XAPPLICATIONNAME=XAPPLICATIONNAME;
        this.jobnum=jobnum;
    }

    public BigInteger getJobnum() {
        return jobnum;
    }

    public void setJobnum(BigInteger  jobnum) {
        this.jobnum = jobnum;
    }

    public String getXAPPLICATION() {
        return XAPPLICATION;
    }

    public void setXAPPLICATION(String XAPPLICATION) {
        this.XAPPLICATION = XAPPLICATION;
    }

    public String getXAPPLICATIONNAME() {
        return XAPPLICATIONNAME;
    }

    public void setXAPPLICATIONNAME(String XAPPLICATIONNAME) {
        this.XAPPLICATIONNAME = XAPPLICATIONNAME;
    }
}
