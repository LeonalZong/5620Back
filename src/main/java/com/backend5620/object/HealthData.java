package com.backend5620.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthData {
    private int healthDataId;

    private int userId; // 用户ID
    private double height; // 身高
    private double weight; // 体重
    private double cholesterol; // 胆固醇
    private int systolicBloodPressure; // 收缩压（高压）
    private int diastolicBloodPressure; // 舒张压（低压）
    private int restingHeartRate; // 静息心率
    private LocalDateTime recordedAt;
}
