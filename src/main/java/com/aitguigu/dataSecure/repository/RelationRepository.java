package com.aitguigu.dataSecure.repository;

import com.aitguigu.dataSecure.entity.Relation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author LucyChen
 * @date 2024-6-10
 * @desc:
 */
@Repository
public interface RelationRepository extends Neo4jRepository<Relation,Long> {
}
