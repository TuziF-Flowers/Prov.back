package com.aitguigu.dataSecure.domain;

import java.math.BigInteger;

public class AppUserjobnum {
    private String XAPPLICATIONNAME;
    private String NAME;
    private String PHONE;
    private String XUNIT1;
    private BigInteger jobnum;

    public String getXAPPLICATIONNAME() {
        return XAPPLICATIONNAME;
    }

    public void setXAPPLICATIONNAME(String XAPPLICATIONNAME) {
        this.XAPPLICATIONNAME = XAPPLICATIONNAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getXUNIT1() {
        return XUNIT1;
    }

    public void setXUNIT1(String XUNIT1) {
        this.XUNIT1 = XUNIT1;
    }

    public BigInteger getJobnum() {
        return jobnum;
    }

    public void setJobnum(BigInteger jobnum) {
        this.jobnum = jobnum;
    }

    public AppUserjobnum(String XAPPLICATIONNAME, String NAME, String PHONE, String XUNIT1, BigInteger jobnum) {
        this.XAPPLICATIONNAME = XAPPLICATIONNAME;
        this.NAME = NAME;
        this.PHONE = PHONE;
        this.XUNIT1 = XUNIT1;
        this.jobnum = jobnum;
    }

}
