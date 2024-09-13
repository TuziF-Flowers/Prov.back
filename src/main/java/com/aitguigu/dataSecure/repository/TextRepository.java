package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Action;
import com.aitguigu.dataSecure.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LucyChen
 * @date 2024-2-15
 * @desc:
 */
public interface TextRepository extends JpaRepository<Action,String> {
}
