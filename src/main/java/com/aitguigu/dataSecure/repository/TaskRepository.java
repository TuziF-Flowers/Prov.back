package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author LucyChen
 * @date 2024-2-4
 * @desc:
 */
public interface TaskRepository extends JpaRepository<Task,String> {
    public Task findTaskById(String id);
}
