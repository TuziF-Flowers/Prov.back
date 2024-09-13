package com.aitguigu.dataSecure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author LucyChen
 * @date 2024-2-15
 * @desc:
 */
@Data
public class Process {
//    private Example extract;
    private Example first_fusion;
    private List<Example> push;
    private List<Example>fusion;
//    private String wbTo;
//    private String wbFrom;
}
