package com.aitguigu.dataSecure.entity;

import lombok.Data;

import java.util.Map;

/**
 * @author LucyChen
 * @date 2024-5-24
 * @desc: 用于指标的计算
 */
@Data
public class Item {
    private int AlreadyRunCnt;
    private int WaitRunCnt;
    private float RunPercent;
    private int RunRecord;
    private int WeiBanCnt;
    private Map<String, Weiban> RunWeiBan;
    private int GuiJiCnt;
    private int GongXiangCnt;
    private int WarnCnt;
    private int WarnPercent;
    private int TuiSong;
    private int RongHe;
    public Item(){
        this.AlreadyRunCnt=0;
        this.WaitRunCnt=0;
        this.RunRecord=0;
        this.GongXiangCnt=0;
        this.GuiJiCnt=0;
        this.WarnCnt=0;
        this.WarnPercent=100;
        this.WeiBanCnt=0;
        this.TuiSong=0;
        this.RongHe=0;
    }
}
