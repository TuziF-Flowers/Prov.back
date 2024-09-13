package com.aitguigu.dataSecure.controller;

import com.aitguigu.dataSecure.entity.*;
import com.aitguigu.dataSecure.entity.Process;
import com.aitguigu.dataSecure.service.LogFileService;
import com.aitguigu.dataSecure.service.ProcessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author LucyChen
 * @date 2024-2-2
 * @desc:
 */
@RestController
@RequestMapping("/api")
public class OperationController {
    private final LogFileService logFileService;
    private final ProcessService processService;

    @Autowired
    public OperationController(LogFileService logFileService,ProcessService processService){
        this.logFileService=logFileService;
        this.processService=processService;
    }

//    @GetMapping("/loadTaskes")
//    @ApiOperation("加载任务表")
//    public void loadTaskes(){
//        processService.loadTaskTable("dataworks-12.csv");
//    }
//
//    @GetMapping("/getAllTaskes")
//    @ApiOperation("获取所有task")
//    public Page<Task> getAllTaskes(@RequestParam(defaultValue = "0")int page){
//        return processService.getAllTaskes(page);
//    }
//
//    @GetMapping("/getAllCommissioner")
//    @ApiOperation("获取所有的委办局[用于筛选]")
//    public Set<String> getAllCommissioner(){
//        return processService.getAllCommissioners();
//    }
//
//    @GetMapping("/getTaskByCommissionerIndex")
//    @ApiOperation("根据前端选择的idx返回数据-委办局")
//    public List<Task>getTaskByCommissionerIndex(String index){
//        return processService.getTaskByCommissionerIndex(index);
//    }
//
    @GetMapping("/getExtract_Jobs")
    @ApiOperation("获取归集jobs")
    public List<Example>getJobs_extract(){
        return processService.getJobs_extract();
    }

    @GetMapping("/getPush_Jobs")
    @ApiOperation("获取推送jobs")
    public List<Process>getJobs_push_top5(){
        return processService.getJobs_push_top5();
    }
//
//    @GetMapping("/getJobBypushId")
//    @ApiOperation("通过pushId获取jobs")
//    public Process getJobByPushId(String pushId){
//        return processService.getJobByPushId(pushId);
//    }
//
//    @GetMapping("/parseLog")
//    public String parseLog() {
//        String logFile="ods_qwjw_peis_older_df推送.txt";
//        processService.parseLogAndSaveToDatabase(logFile);
//        return "Log parsed and saved successfully.";
//    }
//
//    @GetMapping("/getImputaions")
//    public List<Example> getImputations(){
//        return processService.getImputationJobs();
//    }
//
//    @GetMapping("/getFusions")
//    public List<Example_fusion>getFusions(){
//        return processService.getFusionJobs();
//    }
//
//    @GetMapping("/getExtractByTime")
//    public List<Example>getExtractByTime(){return processService.getExtractByTime();}
//
    @GetMapping("/getPushByTime")
    public List<Example>getPushByTime(){return processService.getPushByTime();}

    @GetMapping("/getAllByTime")
    public List<Column>getAllByTime(){return processService.getAllByTime();}

    @GetMapping("/bigscreen")
    public TreeNode generateTreeNode(){
        return processService.generateAllByGuiJi(3);
    }

    @GetMapping("/bigscreen-guiji")
    public TreeNode generateGuiji(){
        return processService.generateGuiji(5);
    }
    @GetMapping("/bigscreen-gongxiang")
    public TreeNode generateGongxiang(){
        return processService.generateGongxiang(5);
    }
    @GetMapping("/generateLinkByOne")
    public TreeNode generateLinkByOne(String id){
        return processService.generateLinkById(id);
    }

    @GetMapping("/getItem")
    public Item getItem(){
        return processService.calItemAll();
    }

    @GetMapping("/calSizeAll")
    public List<Column>calSizeAll(){
        return processService.calSizeAllByTime();
    }

    @GetMapping("/getItemGuiJi")
    public Item getItemGuiJi(){
        return processService.calItemGuiJi();
    }
    @GetMapping("/calSizeGuiJi")
    public List<Column>calSizeGuiJi(){
        return processService.calSizeGuiJiByTime();
    }
    @GetMapping("/getItemGongXiang")
    public Item getItemGongXiangi(){
        return processService.calItemGongXiang();
    }
    @GetMapping("/calSizeGongXiang")
    public List<Column>calSizeGongXiang(){
        return processService.calSizeGongXiangByTime();
    }
}
