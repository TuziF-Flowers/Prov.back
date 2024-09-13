package com.aitguigu.dataSecure.entity;

import lombok.Data;

/**
 * @author LucyChen
 * @date 2024-5-25
 * @desc:
 */
@Data
public class Weiban {
    private int already;
    private int wait;
    public Weiban(){
        this.already=0;
        this.wait=0;
    }
}
