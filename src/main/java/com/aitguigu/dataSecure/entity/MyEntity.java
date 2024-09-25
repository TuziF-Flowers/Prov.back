package com.aitguigu.dataSecure.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@Data
public class MyEntity {
    // 字段和方法
    @Id
    String XAPPLICATION;
    String XAPPLICATIONNAME;
    String NAME;
    String PHONE;
    String XUNIT1;
    BigInteger jobnum;
}
