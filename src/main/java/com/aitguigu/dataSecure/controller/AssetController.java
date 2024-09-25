package com.aitguigu.dataSecure.controller;

import com.aitguigu.dataSecure.domain.*;
import com.aitguigu.dataSecure.entity.Activity;
import com.aitguigu.dataSecure.entity.Application;

import com.aitguigu.dataSecure.entity.MyEntity;
import com.aitguigu.dataSecure.entity.User;
import com.aitguigu.dataSecure.service.AssetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/asset/")
public class AssetController {
    private final AssetService assetService;
    public AssetController(AssetService assetService){
        this.assetService=assetService;
    }

    @GetMapping("/countapp")
    public long getCountapp(){
        return assetService.countDistinctXAPPLICATION();
    }
    @GetMapping("/appfirst")
    public List<Application> getAppfirst(){
        return assetService.findALL();
    }

    @GetMapping("/count-distinct-xunit")
    public List<XunitDTO> getCountDistinctXunit() {
        return assetService.getCountOfDistinctXunitBeforeAt();
    }
    @GetMapping("/name-phone")
    public List<UserDTO> getNameAndPhone() {
        return assetService.getNameAndPhone();
    }
    @GetMapping("/activities/manual")
    public List<ManualDTO> getManualActivities() {
        return assetService.findManualActivitiesWithApplicationNative();
    }
//    @GetMapping("/applications/jobcount")
//    public List<AppjobnumDTO> getApplicationWithJobCount() {
//        return assetService.findApplicationWithJobCountNative();
//    }
//    @GetMapping("/users/jobcount")
//    public List<UserjobnumDTO> getUserWithJobCount() {
//        return assetService.findUserWithJobCount();
//    }
//    @GetMapping("/userapplication/jobcount")
//    public List<AppUserjobnum> getApplicationWithUserAndJobCount() {
//        return assetService.findApplicationWithUserAndJobCount();
//    }
    @GetMapping("/worklog-stats")
    public List<MyEntity> getWorklogStats(
            @RequestParam String col,
            @RequestParam String groupby) {
        return assetService.getWorklogStatistics(col, groupby);
    }

    @GetMapping("/jobcount")
    public List<MyEntity> getJobCount(@RequestParam String params) {
        // 解析 params 字符串为数字列表
        params =sortNumberString(params);
        // 定义变量 col 和 groupBy
        String col = "";
        String groupBy = "";
        // 根据不同的 case 修改 col 和 groupBy 的值
        switch (params){
            case "1":
                col = "application.XAPPLICATION XAPPLICATION,application.XAPPLICATIONNAME";
                groupBy = "application.XAPPLICATION";
                break;
            case "2":
                col = "SUBSTRING_INDEX(XPERSON, '@', 1) NAME,case when LENGTH(SUBSTRING_INDEX(SUBSTRING_INDEX(XPERSON, '@', 2), '@',-1))>11 then null else \n" +
                        "SUBSTRING_INDEX(SUBSTRING_INDEX(XPERSON, '@', 2), '@',-1) end PHONE,SUBSTRING_INDEX(XUNIT, '@', 1) AS XUNIT1";
                groupBy = "XPERSON,SUBSTRING_INDEX(XUNIT, '@', 1)";
                break;
            case "12":
                col = "application.XAPPLICATIONNAME,SUBSTRING_INDEX(XPERSON, '@', 1) NAME,case when LENGTH(SUBSTRING_INDEX(SUBSTRING_INDEX(XPERSON, '@', 2), '@',-1))>11 then null else \n" +
                        "SUBSTRING_INDEX(SUBSTRING_INDEX(XPERSON, '@', 2), '@',-1) end PHONE,SUBSTRING_INDEX(XUNIT, '@', 1) AS XUNIT1";
                groupBy = "B.XAPPLICATION,XPERSON,XUNIT";
                break;
            default:
                col="";
                groupBy="";
                break;
        }
        System.out.println(assetService.getWorklogStatistics(col, groupBy));
        return assetService.getWorklogStatistics(col, groupBy);
        // 处理业务逻辑...


    }
    public static String sortNumberString(String numberString) {
        return Arrays.stream(numberString.split(""))
                .map(Integer::parseInt) // 转换为整数
                .sorted() // 排序
                .map(String::valueOf) // 转换回字符串
                .reduce("", (s1, s2) -> s1 + s2); // 重新组合成字符串
    }
}
