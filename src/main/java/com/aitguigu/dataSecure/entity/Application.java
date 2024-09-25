package com.aitguigu.dataSecure.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="application")
@Data

public class Application {
    @Id
    private String XAPPLICATION;
    private String XAPPLICATIONNAME;
}
