package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application,String> {
    @Query("select count(DISTINCT a.XAPPLICATION) from application a")
    long countDistinctXAPPLICATION();


}
