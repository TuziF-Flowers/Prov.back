package com.aitguigu.dataSecure.service;

import com.aitguigu.dataSecure.domain.UserDTO;
import com.aitguigu.dataSecure.domain.XunitDTO;
import com.aitguigu.dataSecure.entity.Application;

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
}
