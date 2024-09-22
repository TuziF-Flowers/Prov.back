package com.aitguigu.dataSecure.controller;

import com.aitguigu.dataSecure.domain.*;
import com.aitguigu.dataSecure.entity.Activity;
import com.aitguigu.dataSecure.entity.Application;

import com.aitguigu.dataSecure.entity.User;
import com.aitguigu.dataSecure.service.AssetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/applications/jobcount")
    public List<AppjobnumDTO> getApplicationWithJobCount() {
        return assetService.findApplicationWithJobCountNative();
    }
    @GetMapping("/users/jobcount")
    public List<UserjobnumDTO> getUserWithJobCount() {
        return assetService.findUserWithJobCount();
    }
    @GetMapping("/userapplication/jobcount")
    public List<AppUserjobnum> getApplicationWithUserAndJobCount() {
        return assetService.findApplicationWithUserAndJobCount();
    }
}
