package com.aitguigu.dataSecure;

import com.aitguigu.dataSecure.service.LogFileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class Quickstart {

    public static void main(String[] args) {
        SpringApplication.run(Quickstart.class, args);
    }
}
