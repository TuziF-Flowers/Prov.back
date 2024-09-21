package com.aitguigu.dataSecure.controller;

import com.aitguigu.dataSecure.domain.UserDTO;
import com.aitguigu.dataSecure.domain.XunitDTO;
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
    //    @GetMapping("/findactivity")
//    public List<Activity> findActivity(){
//        return assetService.findByACTIVITY("001eb2cd-7847-48f3-a311-32e627891f48");
//    }
//    @GetMapping("/findapplication")
//    public List<Application> findApplication(){
//        return assetService.findByAPPLICATION("0ea6736b-42b1-4235-bbc7-7e7b7379149a");
//    }
//    @GetMapping("/countact")
//    public long getCountact(){
//        return assetService.countDistinctACTIVITY();
//    }
    @GetMapping("/countapp")
    public long getCountapp(){
        return assetService.countDistinctXAPPLICATION();
    }
    @GetMapping("/appfirst")
    public List<Application> getAppfirst(){
        return assetService.findALL();
    }
    //    @GetMapping("/manual")
//    public List<Object[]> getManualActivities() {
//        return assetService.getManualActivities();
//    }
    @GetMapping("/count-distinct-xunit")
    public List<XunitDTO> getCountDistinctXunit() {
        return assetService.getCountOfDistinctXunitBeforeAt();
    }
    @GetMapping("/name-phone")
    public List<UserDTO> getNameAndPhone() {
        return assetService.getNameAndPhone();
    }

}
