package com.aitguigu.dataSecure.service.impl;

import com.aitguigu.dataSecure.domain.UserDTO;
import com.aitguigu.dataSecure.domain.XunitDTO;
import com.aitguigu.dataSecure.entity.Application;
import com.aitguigu.dataSecure.repository.ActivityRepository;
import com.aitguigu.dataSecure.repository.ApplicationRepository;
import com.aitguigu.dataSecure.repository.UserRepository;
import com.aitguigu.dataSecure.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    @Override
//    public List<> getNameAndPhone() {
//        return userRepository.findNameAndPhone();
//    }
//    @Override
//    public List<Activity> findByACTIVITY(String ACTIVITY) {
//        return activityRepository.findActivityByACTIVITY(ACTIVITY);
//    }
//
//    @Override
//    public List<Application> findByAPPLICATION(String XAPPLICATION) {
//        return applicationRepository.findApplicationByXAPPLICATION(XAPPLICATION);
//    }

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

//    @Override
//    public List<Object[]> getManualActivities() {
//        return activityRepository.findManualActivities();
//    }

//    public long countDistinctACTIVITY() {
//        return activityRepository.countDistinctACTIVITY();
//    }

//    @Override
//    public List<Activity> findALL() {
//        return activityRepository.findAll();
//    }
}
