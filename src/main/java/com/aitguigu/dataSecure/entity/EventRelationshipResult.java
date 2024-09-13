package com.aitguigu.dataSecure.entity;

import com.aitguigu.dataSecure.entity.Event;
import com.aitguigu.dataSecure.entity.Relation;
import lombok.Data;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.metadata.schema.Relationship;

/**
 * @author LucyChen
 * @date 2024-6-10
 * @desc:
 */
@Data
public class EventRelationshipResult {
    private Event startEvent;
    private Event endEvent;
    private Relation relationship;
//    public EventRelationshipResult() {}
//
//    public EventRelationshipResult(Event startEvent, Relation relationship, Event endEvent) {
//        this.startEvent = startEvent;
//        this.relationship = relationship;
//        this.endEvent = endEvent;
//    }
}
