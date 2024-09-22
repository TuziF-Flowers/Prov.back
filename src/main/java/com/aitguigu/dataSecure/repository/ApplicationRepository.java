package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,String> {
    @Query("select count(DISTINCT a.XAPPLICATION) from application a")
    long countDistinctXAPPLICATION();
    @Query(value = "SELECT A.XAPPLICATION, A.XAPPLICATIONNAME, COUNT(DISTINCT B.XJOB) AS jobNum " +
            "FROM application A " +
            "LEFT JOIN work B ON A.XAPPLICATION = B.XAPPLICATION " +
            "GROUP BY A.XAPPLICATION, A.XAPPLICATIONNAME " +
            "ORDER BY COUNT(DISTINCT B.XJOB) DESC",
            nativeQuery = true)
    List<Object[]> findApplicationWithJobCountNative();

}
