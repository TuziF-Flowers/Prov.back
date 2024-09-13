package com.aitguigu.dataSecure.service;

import com.aitguigu.dataSecure.entity.EventRelationshipResult;
import com.aitguigu.dataSecure.entity.Relation;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LucyChen
 * @date 2024-6-10
 * @desc:
 */
public interface Neo4jService {
    public List<Relation> getEventsWithRelationsByJobId(String jobId);
}
