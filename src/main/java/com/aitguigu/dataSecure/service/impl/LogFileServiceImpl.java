package com.aitguigu.dataSecure.service.impl;

import com.aitguigu.dataSecure.repository.OperationLogRepository;
import com.aitguigu.dataSecure.entity.operationLog;
import com.aitguigu.dataSecure.service.LogFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author LucyChen
 * @date 2024-2-2
 * @desc:
 */
@Service
public class LogFileServiceImpl implements LogFileService {
    @Autowired
    private OperationLogRepository operationLogRepository;

    public void processLogFile(String filePath){
        try(BufferedReader br=new BufferedReader(new FileReader(filePath))){
            String line;
            while((line=br.readLine())!=null){
                String[] data = line.split("\t", -1); // 制表位分隔，保留空字段
                System.out.println(data);
                operationLog log = new operationLog();
                log.setLogLevel(data[0]);
                log.setTimestamp(LocalDateTime.parse(data[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                log.setIpAddress(data[2]);
                log.setOperatingSystem(data[3]);
                log.setHttpVersion(data[4]);
                log.setUserName(data[5]);
                log.setOperationType(data[6]);
                log.setDetails(data[7]);
                operationLogRepository.save(log);
            }
        }catch (IOException e) {
            e.printStackTrace();
            // 处理异常...
        }
    }
}
