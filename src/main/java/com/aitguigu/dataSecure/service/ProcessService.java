package com.aitguigu.dataSecure.service;

import com.aitguigu.dataSecure.entity.*;
import com.aitguigu.dataSecure.entity.Process;
import org.springframework.data.domain.Page;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author LucyChen
 * @date 2024-2-4
 * @desc:
 */
public interface ProcessService {
    public List<Example>getPushByTime();
    public void getJobsByTypes();
    public List<Example>getJobs_extract();
    public Example getExampleByNodeId(String NodeId);
    public List<Process>getJobs_push_top5();
    public List<Column>getAllByTime();
    public TreeNode generateAllByGuiJi(int number);
    public TreeNode generateGuiji(int number);
    public TreeNode generateGongxiang(int number);
    public TreeNode generateLinkById(String id);
    public Item calItemAll();
    public List<Column>calSizeAllByTime();
    public Item calItemGuiJi();
    public List<Column>calSizeGuiJiByTime();
    public Item calItemGongXiang();
    public List<Column>calSizeGongXiangByTime();
}
