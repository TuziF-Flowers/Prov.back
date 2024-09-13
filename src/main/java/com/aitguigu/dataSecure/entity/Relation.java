package com.aitguigu.dataSecure.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * @author LucyChen
 * @date 2024-6-10
 * @desc:
 */
@Data
@RelationshipEntity(type = "then")
public class Relation {
    @Id
    @GeneratedValue
    private Long identity;

    @StartNode
    private Event start;
    @EndNode
    private Event end;

    @Property("type")
    private String type;
}
