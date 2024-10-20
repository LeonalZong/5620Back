package com.backend5620.object;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthReport {
    private int reportId;
    private int Id;
    private String reportContent;
    private LocalDateTime createdAt;
}
