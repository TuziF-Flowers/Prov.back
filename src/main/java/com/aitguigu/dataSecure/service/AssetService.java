package com.aitguigu.dataSecure.service;

import com.aitguigu.dataSecure.domain.*;
import com.aitguigu.dataSecure.entity.Application;
import com.aitguigu.dataSecure.entity.MyEntity;

import java.util.List;

public interface AssetService {


    public long countDistinctXAPPLICATION();
    public List<Application> findALL();
//    public long countDistinctACTIVITY();
//    public List<Activity> findByACTIVITY(String ACTIVITY);
//    public List<Application> findByAPPLICATION(String XAPPLICATION);
//    public List<Object[]> getManualActivities();
    public List<XunitDTO> getCountOfDistinctXunitBeforeAt();
    public List<UserDTO> getNameAndPhone();
    public List<ManualDTO> findManualActivitiesWithApplicationNative();
    public List<AppjobnumDTO> findApplicationWithJobCountNative();
    public List<UserjobnumDTO> findUserWithJobCount();
    public List<AppUserjobnum> findApplicationWithUserAndJobCount();
    public List<MyEntity> getWorklogStatistics(String col, String groupby);

}
