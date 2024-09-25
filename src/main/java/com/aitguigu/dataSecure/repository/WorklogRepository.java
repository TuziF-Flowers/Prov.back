package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorklogRepository extends JpaRepository<MyEntity, String>, WorklogCustomRepository{
}
