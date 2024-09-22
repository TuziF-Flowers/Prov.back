package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {

    @Query(value = "SELECT A.ACTIVITY, A.ACTIVITYALIAS, A.ACTIVITYNAME, B.XAPPLICATIONNAME " +
            "FROM activity A " +
            "JOIN application B ON A.XAPPLICATION = B.XAPPLICATION " +
            "WHERE A.ACTIVITYTYPE = 'manual'",
            nativeQuery = true)
    List<Object[]> findManualActiviiesWithApplicationNative();
}


