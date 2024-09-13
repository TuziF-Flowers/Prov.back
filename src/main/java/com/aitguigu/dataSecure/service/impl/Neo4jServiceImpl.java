package com.aitguigu.dataSecure.service.impl;

import com.aitguigu.dataSecure.entity.EventRelationshipResult;
import com.aitguigu.dataSecure.entity.Relation;
import com.aitguigu.dataSecure.repository.GraphBigRepository;
import com.aitguigu.dataSecure.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LucyChen
 * @date 2024-6-10
 * @desc:
 */
@Service
public class Neo4jServiceImpl implements Neo4jService {
    @Autowired
    private GraphBigRepository graphBigRepository;

    public List<Relation> getEventsWithRelationsByJobId(String jobId){
        return graphBigRepository.findEventsWithRelationsByJobId(jobId);
    }
}
