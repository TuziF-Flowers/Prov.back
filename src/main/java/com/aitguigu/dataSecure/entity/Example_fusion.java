package com.aitguigu.dataSecure.entity;

import lombok.Data;

import java.util.List;

/**
 * @author LucyChen
 * @date 2024-3-15
 * @desc:
 */
@Data
public class Example_fusion {
    private Example first_fusion;
    private List<Example> fusion;
    private List<Example> push;
    private String wbTo;
}
