package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,String> {

//    @Query("SELECT a.ACTIVITY, a.ACTIVITYALIAS, a.ACTIVITYNAME, app.XAPPLICATIONNAME " +
//            "FROM activity a JOIN a.application app " +
//            "WHERE a.ACTIVITYTYPE = 'manual'")
//    List<Object[]> findManualActivities();
}
