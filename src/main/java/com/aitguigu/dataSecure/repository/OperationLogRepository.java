package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.operationLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LucyChen
 * @date 2024-2-2
 * @desc:
 */
public interface OperationLogRepository extends JpaRepository<operationLog,String> {
}
