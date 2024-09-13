package com.aitguigu.dataSecure.controller;

import com.aitguigu.dataSecure.entity.EventRelationshipResult;
import com.aitguigu.dataSecure.entity.Relation;
import com.aitguigu.dataSecure.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LucyChen
 * @date 2024-6-10
 * @desc:
 */
@RestController
@RequestMapping("/api/neo4j/")
public class Neo4jController {
    private final Neo4jService neo4jService;
    @Autowired
    public Neo4jController(Neo4jService neo4jService){
        this.neo4jService=neo4jService;
    }
    @GetMapping("/events")
    public List<Relation> getEvents() {
        return neo4jService.getEventsWithRelationsByJobId("9026143131");
    }
}
