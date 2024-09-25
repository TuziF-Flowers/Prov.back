package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WorklogCustomRepository  {
    List<MyEntity> findWorklogStatistics(String col, String groupby);
}
