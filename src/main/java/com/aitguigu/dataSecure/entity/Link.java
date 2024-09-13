package com.aitguigu.dataSecure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author LucyChen
 * @date 2024-2-29
 * @desc:
 */
@Entity(name="link")
@Data
public class Link {
    @Id
    private String nodeId;
    private String root;
    private String parent;

    private String children;
    private String paths;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
//    private Timestamp createTime;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
//    private Timestamp updateTime;
}
