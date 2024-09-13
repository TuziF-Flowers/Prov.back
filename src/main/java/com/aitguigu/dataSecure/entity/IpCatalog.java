package com.aitguigu.dataSecure.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author LucyChen
 * @date 2024-5-27
 * @desc:
 */
@Data
@Entity(name="ip_catalog")
public class IpCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 虚拟主键

    private String cl;
    private String datasource;
    private String ip;
}
