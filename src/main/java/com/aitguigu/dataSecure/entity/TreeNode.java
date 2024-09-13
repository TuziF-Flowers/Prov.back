package com.aitguigu.dataSecure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LucyChen
 * @date 2024-4-27
 * @desc:
 */
@Data
public class TreeNode {
    private String id;
    private String name;
    private String label;
    private String text;
    private String status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp variableName;
    private String variableValue;
    private boolean variableUp;
    private Integer mask;
    private String device;
    private String user;
    private List<TreeNode> children;
    private String table;
    public TreeNode() {
        this.children = new ArrayList<>();
    }
    public void addChild(TreeNode child){
        this.children.add(child);
    }
}
