package com.aitguigu.dataSecure.service.impl;

import com.aitguigu.dataSecure.domain.*;
import com.aitguigu.dataSecure.entity.Application;
import com.aitguigu.dataSecure.repository.ActivityRepository;
import com.aitguigu.dataSecure.repository.ApplicationRepository;
import com.aitguigu.dataSecure.repository.UserRepository;
import com.aitguigu.dataSecure.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserRepository userRepository;



    @Override
    public List<UserDTO> getNameAndPhone() {
        List<Object[]> results = userRepository.findNameAndPhone();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (Object[] result : results) {
            String name = (String) result[0];  // 提取 name
            String phone = (String) result[1]; // 提取 phone

            // 创建 UserDTO 并添加到列表中
            userDTOList.add(new UserDTO(name, phone));
        }

        return userDTOList;
    }

    @Override
    public List<ManualDTO> findManualActivitiesWithApplicationNative() {
        List<Object[]> results = activityRepository.findManualActiviiesWithApplicationNative();
        List<ManualDTO> manualDTOList = new ArrayList<>();
        for(Object[] result: results){
            String activity = (String)result[0];
            String activityalias = (String)result[1];
            String activityname = (String)result[2];
            String xapplication = (String)result[3];
            manualDTOList.add(new ManualDTO(activity,activityalias,activityname,xapplication));
        }
        return manualDTOList;
    }

    @Override
    public List<AppjobnumDTO> findApplicationWithJobCountNative() {
        List<Object[]> results = applicationRepository.findApplicationWithJobCountNative();
        List<AppjobnumDTO> appjobnumDTOList = new ArrayList<>();
        for(Object[] result: results){
            String XAPPLICATION = (String)result[0];
            String XAPPLICATIONNAME = (String)result[1];
            BigInteger jobnum = (BigInteger ) result[2];
            appjobnumDTOList.add(new AppjobnumDTO(XAPPLICATION,XAPPLICATIONNAME,jobnum));
        }
        return appjobnumDTOList;
    }

    @Override
    public List<UserjobnumDTO> findUserWithJobCount() {
        List<Object[]> results = userRepository.findUserWithJobCount();
        List<UserjobnumDTO> userjobnumDTOList = new ArrayList<>();
        for(Object[] result:results){
            String name = (String)result[0];
            String phone = (String)result[1];
            String xunit1 = (String)result[2];
            BigInteger jobnum = (BigInteger)result[3];
            userjobnumDTOList.add(new UserjobnumDTO(name,phone,xunit1,jobnum));
        }
        return userjobnumDTOList;
    }

    @Override
    public List<AppUserjobnum> findApplicationWithUserAndJobCount() {
        List<Object[]> results = userRepository.findApplicationWithUserAndJobCount();
        List<AppUserjobnum> AppUserjobnumList =new ArrayList<>();
        for(Object[] result:results){
            String XAPPLICATIONNAME = (String)result[0];
            String NAME = (String)result[1];
            String PHONE = (String)result[2];
            String XUNIT1 = (String)result[3];
            BigInteger jobnum = (BigInteger) result[4];
            AppUserjobnumList.add(new AppUserjobnum(XAPPLICATIONNAME,NAME,PHONE,XUNIT1,jobnum));
        }
        return AppUserjobnumList;
    }


    public long countDistinctXAPPLICATION()
    {
        return applicationRepository.countDistinctXAPPLICATION();
    }

    @Override
    public List<Application> findALL() {
        return applicationRepository.findAll();
    }

    @Override
    public List<XunitDTO> getCountOfDistinctXunitBeforeAt() {
        List<Object[]> results = userRepository.findNameAndPhone();
        List<XunitDTO> XunitDTOList = new ArrayList<>();
        for (Object[] result : results) {
            String xunit = (String) result[0];  // 提取 name


            // 创建 UserDTO 并添加到列表中
            XunitDTOList.add(new XunitDTO(xunit));
        }

        return XunitDTOList;
    }


}
