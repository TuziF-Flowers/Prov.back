package com.aitguigu.dataSecure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LucyChen
 * @date 2024-2-2
 * @desc:
 */

public interface LogFileService {
    public void processLogFile(String filePath);
}
