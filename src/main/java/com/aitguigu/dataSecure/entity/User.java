package com.aitguigu.dataSecure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="user")
@Data
public class User {
    @Id
    private String XIDENTITY;
    private String XPERSON;
    private String XUNIT;
    private String NAME;
    private String PHONE;
}
