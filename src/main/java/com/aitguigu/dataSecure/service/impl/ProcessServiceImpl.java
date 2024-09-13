package com.aitguigu.dataSecure.service.impl;

import com.aitguigu.dataSecure.entity.*;
import com.aitguigu.dataSecure.entity.Process;
import com.aitguigu.dataSecure.repository.*;
import com.aitguigu.dataSecure.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author LucyChen
 * @date 2024-2-4
 * @desc:
 */
@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private TextRepository textRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private IpCatalogRepository ipCatalogRepository;

    List<Example> extract = new ArrayList<>();
    List<Example> fusion = new ArrayList<>();
    List<Example> push = new ArrayList<>();

    //该段函数只需要运行一次，请求量大，会影响处理时间
    public void getJobsByTypes() {
        //type=1 抽取任务 type=2融合任务 type=3 推送任务
        extract.clear();
        fusion.clear();
        push.clear();
        List<Job> jobs = jobRepository.findAll();
        for (Job job : jobs) {
            Task task = taskRepository.findById(job.getNodeId()).orElse(null);
            Result result = resultRepository.findById(job.getId()).orElse(null);
            Example example = new Example();
            example.setTask(task);
            example.setJob(job);
            example.setResult(result);
            assert task != null;
            String taskType = task.getType();
            //需要更改！！！
            if (taskType.equals("1")) {
                extract.add(example);
            } else if (taskType.equals("2")) {
                fusion.add(example);
            } else {
                push.add(example);
            }
        }
    }


    // 获取归集任务——服务归集任务页面+全局页面的归集部分
    public List<Example> getJobs_extract() {
        getJobsByTypes();
        if (extract.size() > 5) {
            return extract.subList(0, 5);
        } else return extract;
    }

    public Example getExampleByNodeId(String NodeId) {
        Example example = new Example();
        Task task = taskRepository.findById(NodeId).orElse(null);
        List<Job> jobs = jobRepository.findJobsByNodeId(NodeId);
        Job latest = null;
        if (!jobs.isEmpty()) {
            // 使用 Stream API 从 tasks 中找到 bizDate 最近的 Task
            latest = jobs.stream()
                    .max(Comparator.comparing(Job::getBizdate))
                    .orElse(null);
        }
        if (latest != null) {
            Result result = resultRepository.findById(latest.getId()).orElse(null);
            example.setResult(result);
        }
        example.setTask(task);
        example.setJob(latest);
        return example;
    }

    // 获取共享任务——服务共享任务页面+全局页面的共享部分
    public List<Process> getJobs_push_top5() {
        if (extract.size() == 0) getJobsByTypes();
        List<Process> processes = new ArrayList<>();
        //67951 67953 68001
        //67951 68014 68160
        Process process = new Process();
        List<Example> fusion_examples = new ArrayList<>();
        Example fusionnn = getExampleByNodeId("67953");
        fusion_examples.add(fusionnn);
        fusionnn = getExampleByNodeId("68014");
        fusion_examples.add(fusionnn);
        process.setFusion(fusion_examples);

        fusion_examples = new ArrayList<>();
        fusionnn = getExampleByNodeId("68001");
        fusion_examples.add(fusionnn);
        fusionnn = getExampleByNodeId("78054");
        fusion_examples.add(fusionnn);
        process.setPush(fusion_examples);
        fusionnn = getExampleByNodeId("67951");
        process.setFirst_fusion(fusionnn);
        processes.add(process);

        //67223 67225 67228
        process = new Process();
        fusion_examples = new ArrayList<>();
        fusionnn = getExampleByNodeId("67225");
        fusion_examples.add(fusionnn);
        process.setFusion(fusion_examples);
        fusion_examples = new ArrayList<>();
        fusionnn = getExampleByNodeId("67228");
        fusion_examples.add(fusionnn);
        process.setPush(fusion_examples);
        fusionnn = getExampleByNodeId("67223");
        process.setFirst_fusion(fusionnn);
        processes.add(process);

        //67224 67230 67231
        //67224 73805 73983
        process = new Process();
        fusion_examples = new ArrayList<>();
        fusionnn = getExampleByNodeId("67230");
        fusion_examples.add(fusionnn);
        fusionnn = getExampleByNodeId("73805");
        fusion_examples.add(fusionnn);
        process.setFusion(fusion_examples);
        fusion_examples = new ArrayList<>();
        fusionnn = getExampleByNodeId("67231");
        fusion_examples.add(fusionnn);
        fusionnn = getExampleByNodeId("73983");
        fusion_examples.add(fusionnn);
        process.setPush(fusion_examples);
        fusionnn = getExampleByNodeId("67224");
        process.setFirst_fusion(fusionnn);
        processes.add(process);
        return processes;
    }

    public class JobComparator implements Comparator<Example> {
        @Override
        public int compare(Example example1, Example example2) {
            return example1.getResult().getStartTime().compareTo(example2.getResult().getStartTime());
        }
    }

    public List<Example>getPushByTime(){
        if(push.size()==0) getJobsByTypes();
        List<Example>exampleList=new ArrayList<>();
        exampleList.addAll(push);
        exampleList.addAll(fusion);
        Collections.sort(exampleList, new JobComparator());
        return exampleList;
    }

    public List<Column>getAllByTime(){
        List<Column>tables=new ArrayList<>();
//        System.out.println(getTime());
        List<Job>jList=jobRepository.findJobsByBizdateEquals(getTime());
        List<String> givenList = Arrays.asList("67941", "67951", "67953", "68001", "68014", "78054", "67222", "67223", "67225", "67228", "67093", "67224", "67230", "67231", "73805", "73983");
        List<Job> jobList = jList.stream()
                .filter(job -> givenList.contains(job.getNodeId()))
                .collect(Collectors.toList());

//        System.out.println(jobList);
        Collections.sort(jobList, new Comparator<Job>() {
            @Override
            public int compare(Job p1, Job p2) {
                Result result=resultRepository.findById(p1.getId()).orElse(null);
                Timestamp time1=result.getStartTime();
                result=resultRepository.findById(p2.getId()).orElse(null);
                Timestamp time2=result.getStartTime();
                return time2.compareTo(time1);
            }
        });
        for(Job job:jobList){
            Column current=new Column();
            Task task=taskRepository.findById(job.getNodeId()).orElse(null);
            Result result=resultRepository.findById(job.getId()).orElse(null);
            Code code=codeRepository.findById(job.getNodeId()).orElse(null);
            current.setId(job.getId());
            current.setNodeId(job.getNodeId());
            current.setTaskName(task.getTaskName());
            Pattern pattern = Pattern.compile("_(.*?)_");
            Matcher matcher = pattern.matcher(task.getTaskName());
//            String weiban = "";
//            if (matcher.find()) {
//                weiban = matcher.group(1);
//            }
//            current.setWeiban(weiban);
//            task.setWeiban(weiban);
            pattern=Pattern.compile("[\\u4e00-\\u9fa5].*");
            matcher = pattern.matcher(task.getTaskName());
            if (matcher.find()) {
                current.setTaskName(matcher.group());
            }else{
                current.setTaskName(task.getTaskName());
            }
            current.setStatus(job.getStatus());
            current.setPrgType(task.getType());
            current.setStartTime(result.getStartTime());
            current.setRecordCnt(result.getRecordCnt());
            current.setTotalTime(result.getTotalTime());
            current.setDevice("未知");
            current.setTable(code.getTable1());
            current.setUser("未知");
            current.setWeiban(task.getWeiban());
            tables.add(current);
        }
        return tables;
    }

    private TreeNode generateOneNode(Job job){
        TreeNode node=new TreeNode();
        Task task=taskRepository.findById(job.getNodeId()).orElse(null);
        Result result=resultRepository.findById(job.getId()).orElse(null);
        Code code=codeRepository.findById(job.getNodeId()).orElse(null);
        node.setId(job.getId());
        assert task != null;
        assert result != null;
        node.setName(task.getTaskName());
        Pattern pattern=Pattern.compile("[\\u4e00-\\u9fa5].*");
        Matcher matcher = pattern.matcher(task.getTaskName());
        if (matcher.find()) {
            node.setName(matcher.group());
        }else{
            node.setName(task.getTaskName());
        }
        node.setText(job.getNodeId());
        node.setVariableName(result.getStartTime());
        node.setMask(3);
        String datasource;
        node.setDevice("无");
        node.setUser("用户-未知");
        List<IpCatalog>listsss=new ArrayList<>();
        switch (task.getType()) {
            case "23":
                node.setLabel("抽取");
                node.setStatus("B");
                node.setVariableUp(true);
                node.setVariableValue(result.getRecordCnt());
                node.setTable(code.getTable1());
                datasource=code.getDatasource1();
                System.out.println(datasource);
                listsss=ipCatalogRepository.findIpCatalogsByDatasource(datasource);
                System.out.println(listsss);
                if(listsss.size()!=0){
                    String device= listsss.get(0).getIp();
                    node.setDevice(device);
                }
                break;
            case "228":
                node.setLabel("推送");
                node.setStatus("G");
                node.setVariableUp(false);
                node.setVariableValue(result.getRecordCnt());
                node.setTable(code.getTable1());
                //增加委办信息
                TreeNode weiban_to=new TreeNode();
                weiban_to.setId("to"+job.getId());
                weiban_to.setName(task.getWeiban());
                weiban_to.setMask(5);
                weiban_to.setLabel(task.getWeiban());
                node.addChild(weiban_to);
                datasource=code.getDatasource2();
                System.out.println(datasource);
                System.out.println(node);
                listsss=ipCatalogRepository.findIpCatalogsByDatasource(datasource);
                if(listsss.size()!=0){
                    String device= listsss.get(0).getIp();
                    node.setDevice(device);
                }
                break;
            case "227":
                node.setLabel("融合");
                node.setStatus("Y");
                node.setVariableUp(true);
                node.setVariableValue(result.getTotalTime());
                break;
        }
        return node;
    }

    private TreeNode addLabels(Job job,TreeNode node){
        Task task=taskRepository.findById(job.getNodeId()).orElse(null);
        Result result=resultRepository.findById(job.getId()).orElse(null);
        node.setId(job.getId());
        assert task != null;
        assert result != null;
        switch (task.getType()) {
            case "23":
                //增加前置机信息
                TreeNode leaf=new TreeNode();
                leaf.setId("desktop"+job.getId());
                leaf.setLabel(task.getDatabase());
                leaf.setName("root");
                leaf.setMask(2);
                //增加委办信息
                TreeNode leaf_2=new TreeNode();
                leaf_2.setId("mysql"+job.getId());
                leaf_2.setName("weiban");
                leaf_2.setMask(1);
                leaf_2.setLabel(task.getWeiban());
                leaf.addChild(leaf_2);
                node.addChild(leaf);
                break;
        }
        return node;
    }

    private TreeNode findOrCreateChild(TreeNode parent,String text,String date,boolean flag){
        for(TreeNode child:parent.getChildren()){
            if(child.getText().equals(text)){
                return child;
            }
        }
        Job job=jobRepository.findJobByBizdateAndNodeId(date,text);
        List<String> givenList = Arrays.asList("67941", "67951", "67953", "68001", "68014", "78054", "67222", "67223", "67225", "67228", "67093", "67224", "67230", "67231", "73805", "73983");
        TreeNode newChild=new TreeNode();
        if(job!=null) {
            if (!givenList.contains(job.getNodeId())) {
                return new TreeNode();
            }
            newChild=generateOneNode(job);
            if(flag) newChild=addLabels(job,newChild);
            parent.addChild(newChild);
        }
        return newChild;
    }

    private String getTime(){
        //01-筛选当日job
//        LocalDate today = LocalDate.now().minusDays(1);
        //**目前没有实际生产数据，暂用2024-04-02测试！！
        LocalDate today=LocalDate.of(2024, 4, 2);
        LocalDateTime datetime = today.atStartOfDay();
        ZoneId utcZone = ZoneId.of("UTC");
        ZonedDateTime zonedDateTime = datetime.atZone(utcZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = zonedDateTime.format(formatter);
        return formattedDateTime;
    }

    private TreeNode generateRoot(){
        TreeNode root=new TreeNode();
        // 根节点信息
        root.setId("g1");
        root.setName("数据湖");
        root.setLabel("数据湖");
        root.setMask(4);
        return root;
    }

    private List<Job> generateLeftByexecuteTime(int number){
        String formattedDateTime=getTime();
        List<Job>jList=jobRepository.findJobsByBizdateEquals(formattedDateTime);
        List<String> givenList = Arrays.asList("67941", "67951", "67953", "68001", "68014", "78054", "67222", "67223", "67225", "67228", "67093", "67224", "67230", "67231", "73805", "73983");
        List<Job> jobList = jList.stream()
                .filter(job -> givenList.contains(job.getNodeId()))
                .collect(Collectors.toList());

        //02-筛选最近的三条归集记录
        List<Job>guiJiList=new ArrayList<>();
        for(Job job:jobList){
            Task task=taskRepository.findById(job.getNodeId()).orElse(null);
            String prgType=task.getType();
            if(prgType.equals("23")){
                guiJiList.add(job);
            }
        }
        Collections.sort(guiJiList, new Comparator<Job>() {
            @Override
            public int compare(Job p1, Job p2) {
                Result result=resultRepository.findById(p1.getId()).orElse(null);
                Timestamp time1=result.getStartTime();
                result=resultRepository.findById(p2.getId()).orElse(null);
                Timestamp time2=result.getStartTime();
                return time2.compareTo(time1);
            }
        });
        if(guiJiList.size()>number){
            guiJiList=guiJiList.subList(0,number);
        }
        return guiJiList;
    }

    public TreeNode generateAllByGuiJi(int number){
        TreeNode root=generateRoot();
        List<Job>guiJiList=generateLeftByexecuteTime(number);
        List<TreeNode>setA=new ArrayList<>();
        // 03-构建归集叶子节点树
        for(Job job:guiJiList){
            TreeNode child=generateOneNode(job);
            child=addLabels(job,child);
            setA.add(child);
        }
        List<String> givenList = Arrays.asList("67941", "67951", "67953", "68001", "68014", "78054", "67222", "67223", "67225", "67228", "67093", "67224", "67230", "67231", "73805", "73983");
        // 04-构建共享叶子节点树
        for (Job job:guiJiList){
            if (!givenList.contains(job.getNodeId())) {
                continue;
            }
            String paths=linkRepository.findLinkByNodeId(job.getNodeId()).getPaths();
            String[] parts = paths.split(",");
            String[][] res = new String[parts.length][];
            for (int i = 0; i < parts.length; i++) {
                res[i] = parts[i].split("-");
            }
            for(int i=0;i<res.length;i++){
                TreeNode current=root;
                for(int j=1;j<res[i].length;j++){
                    TreeNode child=findOrCreateChild(current,res[i][j],getTime(),true);
                    current=child;
                }
            }
        }
        for(TreeNode set:setA){
            root.addChild(set);
        }
        return root;
    }

    public TreeNode generateGuiji(int number){
        TreeNode root=generateRoot();
        List<Job>guiJiList=generateLeftByexecuteTime(number);
        List<TreeNode>setA=new ArrayList<>();
        // 03-构建归集叶子节点树
        for(Job job:guiJiList){
            TreeNode child=generateOneNode(job);
            child=addLabels(job,child);
            setA.add(child);
        }
        for(TreeNode set:setA){
            root.addChild(set);
        }
        return root;
    }

    public TreeNode generateGongxiang(int number){
        TreeNode root=generateRoot();
        List<Job>jList=jobRepository.findJobsByBizdateEquals(getTime());
        List<String> givenList = Arrays.asList("67941", "67951", "67953", "68001", "68014", "78054", "67222", "67223", "67225", "67228", "67093", "67224", "67230", "67231", "73805", "73983");
        List<Job> jobList = jList.stream()
                .filter(job -> givenList.contains(job.getNodeId()))
                .collect(Collectors.toList());
        List<Job>gongxiang=new ArrayList<>();
        for(Job job:jobList){
            if (!givenList.contains(job.getNodeId())) {
                continue;
            }
            Task task=taskRepository.findById(job.getNodeId()).orElse(null);
            String prgType=task.getType();
            if(prgType.equals("228")){
                gongxiang.add(job);
            }
        }
        Collections.sort(gongxiang, new Comparator<Job>() {
            @Override
            public int compare(Job p1, Job p2) {
                Result result=resultRepository.findById(p1.getId()).orElse(null);
                Timestamp time1=result.getStartTime();
                result=resultRepository.findById(p2.getId()).orElse(null);
                Timestamp time2=result.getStartTime();
                return time2.compareTo(time1);
            }
        });
        if(gongxiang.size()>number){
            gongxiang=gongxiang.subList(0,number);
        }
        // 04-构建共享叶子节点树
        for (Job job:gongxiang){
            String rt=linkRepository.findLinkByNodeId(job.getNodeId()).getRoot();
            String paths=linkRepository.findLinkByNodeId(rt).getPaths();
            String[] parts = paths.split(",");
            String[][] res = new String[parts.length][];
            for (int i = 0; i < parts.length; i++) {
                res[i] = parts[i].split("-");
            }
            for(int i=0;i<res.length;i++){
                TreeNode current=root;
                for(int j=1;j<res[i].length;j++){
                    TreeNode child=findOrCreateChild(current,res[i][j],getTime(),true);
                    current=child;
                }
            }
        }
        return root;
    }

    public TreeNode generateLinkById(String id){
        TreeNode root_new=new TreeNode();
        root_new.setLabel("委办局数据库");
        root_new.setId("mysql");
        Job job=jobRepository.findById(id).orElse(null);
        Link link=linkRepository.findLinkByNodeId(job.getNodeId());
        Link link_root=linkRepository.findLinkByNodeId(link.getRoot());
        Task task=taskRepository.findById(link.getRoot()).orElse(null);
        root_new.setName(task.getWeiban());
        String paths=link_root.getPaths();
        String[] parts = paths.split(",");
        String[][] res = new String[parts.length][];
        for (int i = 0; i < parts.length; i++) {
            if(parts[i].contains(job.getNodeId())){
                res[i] = parts[i].split("-");
            }
        }
        for (String[] re : res) {
            TreeNode current = root_new;
            if(re==null||  re.length==0) continue;
            for (String s : re) {
                TreeNode child = findOrCreateChild(current, s, getTime(),false);
                current = child;
            }
        }
        return root_new;
    }

    public Item calItemAll(){
        String bizdate=getTime();
        List<Job>jList=jobRepository.findJobsByBizdateAndType(bizdate,"0");
        List<String> givenList = Arrays.asList("67941", "67951", "67953", "68001", "68014", "78054", "67222", "67223", "67225", "67228", "67093", "67224", "67230", "67231", "73805", "73983");
        List<Job> jobsList = jList.stream()
                .filter(job -> givenList.contains(job.getNodeId()))
                .collect(Collectors.toList());
        Map<String, Weiban> weibanMap = new HashMap<>();
        Item item=new Item();
        int AlreadyRunCnt=0;
        int WaitRunCnt=0;
        float RunPercent;
        int RunRecord=0;
        for(Job job:jobsList){
            if(job.getStatus().equals("6")){
                AlreadyRunCnt++;
            }else if(job.getStatus().equals("1")){
                WaitRunCnt++;
            }
            Result result=resultRepository.findById(job.getId()).orElse(null);
            assert result != null;
//            System.out.println(result);
            if (result.getRecordCnt() != null && !result.getRecordCnt().trim().isEmpty()){
                RunRecord+=Integer.parseInt(result.getRecordCnt());
            }
            String weiban = taskRepository.findTaskById(job.getNodeId()).getWeiban();
            if (weiban != null) {
                // 更新 weiban 的统计信息
                Weiban status = weibanMap.getOrDefault(weiban, new Weiban());
                if (job.getStatus().equals("6")) {
                    status.setAlready(status.getAlready() + 1);
                } else if (job.getStatus().equals("1")) {
                    status.setWait(status.getWait() + 1);
                }
                weibanMap.put(weiban, status);
            }
            Task task=taskRepository.findTaskById(job.getNodeId());
            if(task.getType().equals("23")){
                item.setGuiJiCnt(item.getGuiJiCnt()+1);
            }else if(task.getType().equals("227")){
                item.setRongHe(item.getRongHe()+1);
            }else if(task.getType().equals("228")){
                item.setTuiSong(item.getTuiSong()+1);
            }
        }
        Map<String, Weiban> sortedMap = weibanMap.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().getAlready(), e1.getValue().getAlready()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
//        System.out.println(sortedMap);
        item.setGongXiangCnt(item.getRongHe()+item.getTuiSong());
        item.setWeiBanCnt(weibanMap.size());
        item.setRunWeiBan(sortedMap);
        RunPercent=((float)AlreadyRunCnt/jobsList.size())*100;
        item.setAlreadyRunCnt(AlreadyRunCnt);
        item.setWaitRunCnt(WaitRunCnt);
        item.setRunPercent(RunPercent);
        item.setRunRecord(RunRecord);
        return item;
    }

    public List<Column>calSizeAllByTime(){
        List<Column>list=getAllByTime();
        list.removeIf(column -> column.getRecordCnt() == null);
        list.removeIf(column -> column.getRecordCnt().equals(""));
        Collections.sort(list, Comparator.comparing(Column::getRecordCnt).reversed());
        if(list.size()>10) {
            return list.subList(0, 10);
        }else return list;
    }

    public Item calItemGuiJi(){
        String bizdate=getTime();
        List<Job>jList=jobRepository.findJobsByBizdateAndType(bizdate,"0");
        List<String> givenList = Arrays.asList("67941", "67951", "67953", "68001", "68014", "78054", "67222", "67223", "67225", "67228", "67093", "67224", "67230", "67231", "73805", "73983");
        List<Job> jobsList = jList.stream()
                .filter(job -> givenList.contains(job.getNodeId()))
                .collect(Collectors.toList());
        Map<String, Weiban> weibanMap = new HashMap<>();
        Item item=new Item();
        int AlreadyRunCnt=0;
        int WaitRunCnt=0;
        float RunPercent;
        int RunRecord=0;
        Iterator<Job> iterator = jobsList.iterator();
        while (iterator.hasNext()) {
            Job job = iterator.next();
            Task task = taskRepository.findTaskById(job.getNodeId());
            if (task.getType().equals("23")) {
                item.setGuiJiCnt(item.getGuiJiCnt() + 1);
            } else {
                iterator.remove(); // 使用迭代器的 remove 方法移除当前元素
            }
        }
        for(Job job:jobsList){
            if(job.getStatus().equals("6")){
                AlreadyRunCnt++;
            }else if(job.getStatus().equals("1")){
                WaitRunCnt++;
            }
            Result result=resultRepository.findById(job.getId()).orElse(null);
            assert result != null;
            if (result.getRecordCnt() != null && !result.getRecordCnt().trim().isEmpty()) {
                RunRecord+=Integer.parseInt(result.getRecordCnt());
            }
            String weiban = taskRepository.findTaskById(job.getNodeId()).getWeiban();
            if (weiban != null) {
                // 更新 weiban 的统计信息
                Weiban status = weibanMap.getOrDefault(weiban, new Weiban());
                if (job.getStatus().equals("6")) {
                    status.setAlready(status.getAlready() + 1);
                } else if (job.getStatus().equals("1")) {
                    status.setWait(status.getWait() + 1);
                }
                weibanMap.put(weiban, status);
            }
        }
        Map<String, Weiban> sortedMap = weibanMap.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().getAlready(), e1.getValue().getAlready()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
//        System.out.println(sortedMap);
        item.setWeiBanCnt(weibanMap.size());
        item.setRunWeiBan(sortedMap);
        RunPercent=((float)AlreadyRunCnt/jobsList.size())*100;
        item.setAlreadyRunCnt(AlreadyRunCnt);
        item.setWaitRunCnt(WaitRunCnt);
        item.setRunPercent(RunPercent);
        item.setRunRecord(RunRecord);
        return item;
    }
    public List<Column>calSizeGuiJiByTime(){
        List<Column>list=getAllByTime();
        list.removeIf(column -> !column.getPrgType().equals("23"));
        list.removeIf(column -> column.getRecordCnt() == null);
        list.removeIf(column -> column.getRecordCnt().equals(""));
        Collections.sort(list, Comparator.comparing(Column::getRecordCnt).reversed());
        if(list.size()>10) {
            return list.subList(0, 10);
        }else return list;
    }
    public Item calItemGongXiang(){
        String bizdate=getTime();
        List<Job>jList=jobRepository.findJobsByBizdateAndType(bizdate,"0");
        List<String> givenList = Arrays.asList("67941", "67951", "67953", "68001", "68014", "78054", "67222", "67223", "67225", "67228", "67093", "67224", "67230", "67231", "73805", "73983");
        List<Job> jobsList = jList.stream()
                .filter(job -> givenList.contains(job.getNodeId()))
                .collect(Collectors.toList());
        Map<String, Weiban> weibanMap = new HashMap<>();
        Item item=new Item();
        int AlreadyRunCnt=0;
        int WaitRunCnt=0;
        float RunPercent;
        int RunRecord=0;
        Iterator<Job> iterator = jobsList.iterator();
        while (iterator.hasNext()) {
            Job job = iterator.next();
            Task task = taskRepository.findTaskById(job.getNodeId());
            if (task.getType().equals("227")) {
                item.setRongHe(item.getRongHe() + 1);
            } else if(task.getType().equals("228")){
                item.setTuiSong(item.getTuiSong()+1);
            } else{
                iterator.remove(); // 使用迭代器的 remove 方法移除当前元素
            }
        }
        item.setGongXiangCnt(item.getRongHe()+item.getTuiSong());
        for(Job job:jobsList){
            if(job.getStatus().equals("6")){
                AlreadyRunCnt++;
            }else if(job.getStatus().equals("1")){
                WaitRunCnt++;
            }
            Result result=resultRepository.findById(job.getId()).orElse(null);
            assert result != null;
            if (result.getRecordCnt() != null && !result.getRecordCnt().trim().isEmpty()) {
                RunRecord+=Integer.parseInt(result.getRecordCnt());
            }
            String weiban = taskRepository.findTaskById(job.getNodeId()).getWeiban();
            if (weiban != null) {
                // 更新 weiban 的统计信息
                Weiban status = weibanMap.getOrDefault(weiban, new Weiban());
                if (job.getStatus().equals("6")) {
                    status.setAlready(status.getAlready() + 1);
                } else if (job.getStatus().equals("1")) {
                    status.setWait(status.getWait() + 1);
                }
                weibanMap.put(weiban, status);
            }
            item.setGuiJiCnt(item.getGuiJiCnt()+1);
        }
        Map<String, Weiban> sortedMap = weibanMap.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().getAlready(), e1.getValue().getAlready()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
//        System.out.println(sortedMap);
        item.setWeiBanCnt(weibanMap.size());
        item.setRunWeiBan(sortedMap);
        RunPercent=((float)AlreadyRunCnt/jobsList.size())*100;
        item.setAlreadyRunCnt(AlreadyRunCnt);
        item.setWaitRunCnt(WaitRunCnt);
        item.setRunPercent(RunPercent);
        item.setRunRecord(RunRecord);
        return item;
    }
    public List<Column>calSizeGongXiangByTime(){
        List<Column>list=getAllByTime();
        list.removeIf(column -> column.getPrgType().equals("23"));
        list.removeIf(column -> column.getRecordCnt() == null);
        list.removeIf(column -> column.getRecordCnt().equals(""));
        Collections.sort(list, Comparator.comparing(Column::getRecordCnt).reversed());
        if(list.size()>10) {
            return list.subList(0, 10);
        }else return list;
    }
}

