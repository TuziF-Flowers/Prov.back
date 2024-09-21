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

}