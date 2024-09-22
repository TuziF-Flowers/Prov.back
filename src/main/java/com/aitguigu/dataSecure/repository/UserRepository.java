package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
//    @Query("SELECT count(DISTINCT SUBSTRING_INDEX(XUNIT, '@', 1)) FROM user")
//    long countByXUNIT();
//    long countDistinctByXPERSON();
    @Query(value = "SELECT DISTINCT SUBSTRING_INDEX(u.XUNIT, '@', 1) FROM user u", nativeQuery = true)
    List<Object[]> countDistinctXunitBeforeAt();
    @Query(value = "SELECT SUBSTRING_INDEX(u.xperson, '@', 1) AS name, " +
            "CASE " +
            "  WHEN LENGTH(SUBSTRING_INDEX(SUBSTRING_INDEX(u.xperson, '@', 2), '@', -1)) > 11 " +
            "  THEN NULL " +
            "  ELSE SUBSTRING_INDEX(SUBSTRING_INDEX(u.xperson, '@', 2), '@', -1) " +
            "END AS phone " +
            "FROM user u", nativeQuery = true)
    List<Object[]> findNameAndPhone();
    @Query(value = "SELECT DISTINCT A.NAME, A.PHONE, SUBSTRING_INDEX(A.XUNIT, '@', 1) AS XUNIT1, " +
            "COUNT(DISTINCT B.XJOB) AS jobnum " +
            "FROM user A " +
            "LEFT JOIN worklog_fact B ON A.XIDENTITY = B.XIDENTITY " +
            "GROUP BY A.NAME, A.PHONE, A.XPERSON, SUBSTRING_INDEX(A.XUNIT, '@', 1) " +
            "ORDER BY COUNT(DISTINCT B.XJOB) DESC",
            nativeQuery = true)
    List<Object[]> findUserWithJobCount();
    @Query(value = "SELECT DISTINCT application.XAPPLICATIONNAME, A.NAME, A.PHONE, " +
            "SUBSTRING_INDEX(A.XUNIT, '@', 1) AS XUNIT1, " +
            "COUNT(DISTINCT B.XJOB) AS jobnum " +
            "FROM user A " +
            "JOIN worklog_fact B ON A.XIDENTITY = B.XIDENTITY " +
            "JOIN application ON B.XAPPLICATION = application.XAPPLICATION " +
            "GROUP BY application.XAPPLICATIONNAME, A.NAME, A.PHONE, SUBSTRING_INDEX(A.XUNIT, '@', 1) " +
            "ORDER BY COUNT(DISTINCT B.XJOB) DESC",
            nativeQuery = true)
    List<Object[]> findApplicationWithUserAndJobCount();

}