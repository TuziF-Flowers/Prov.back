package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author LucyChen
 * @date 2024-2-15
 * @desc:
 */
public interface JobRepository extends JpaRepository<Job,String> {
    public List<Job> findJobsByNodeId(String NodeId);
    public List<Job> findJobsByBizdateEquals(String bizdate);
    public Job findJobByBizdateAndNodeId(String bizdate,String NodeId);
    public List<Job>findJobsByBizdateAndType(String bizdate,String type);
}

