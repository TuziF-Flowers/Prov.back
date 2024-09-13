package com.aitguigu.dataSecure.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author LucyChen
 * @date 2024-2-2
 * @desc:
 */
@Entity(name="operation_log")
@Data
public class operationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logLevel;
    private LocalDateTime timestamp;
    private String ipAddress;
    private String operatingSystem;
    private String httpVersion;
    private String userName;
    private String operationType;
    private String details;
}
