package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Event;
import com.aitguigu.dataSecure.entity.EventRelationshipResult;
import com.aitguigu.dataSecure.entity.Relation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LucyChen
 * @date 2024-6-10
 * @desc:
 */
@Repository
public interface GraphBigRepository extends Neo4jRepository<EventRelationshipResult,Long> {

//    @Query("MATCH (n:Event)-[r]->(m) WHERE n.jobid=$jobId RETURN n, r, m")
//    List<EventRelationshipResult> findEventsWithRelationsByJobId(String jobId);

//    @Query("MATCH (n:Event) WHERE n.jobid=$jobId RETURN n")
    @Query("MATCH (n:Event)-[r:then]->(m:Event) WHERE n.jobid=$jobId RETURN n AS startEvent, r AS relationship, m AS endEvent")
    List<Relation> findEventsWithRelationsByJobId(@Param("jobId") String jobId);
}
