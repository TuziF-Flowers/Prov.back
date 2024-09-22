package com.aitguigu.dataSecure.domain;

import java.math.BigInteger;

public class UserjobnumDTO {
    private String name;
    private String phone;
    private String xunit1;
    private BigInteger jobnum;

    public UserjobnumDTO(String name,String phone,String xunit1,BigInteger jobnum) {
        this.name = name;
        this.phone = phone;
        this.xunit1 = xunit1;
        this.jobnum = jobnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getXunit1() {
        return xunit1;
    }

    public void setXunit1(String xunit1) {
        this.xunit1 = xunit1;
    }

    public BigInteger getJobnum() {
        return jobnum;
    }

    public void setJobnum(BigInteger jobnum) {
        this.jobnum = jobnum;
    }
}