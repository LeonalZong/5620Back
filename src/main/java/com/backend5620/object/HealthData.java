package com.backend5620.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthData {
    private int healthDataId;     // 健康数据的唯一ID
    private String gender;
    private int age;
    private int userId;           // 用户ID
    private double height;        // 身高
    private double weight;        // 体重
    private double cholesterol;   // 胆固醇
    private double systolicBloodPressure; // 收缩血压
    private double diastolicBloodPressure; //舒张血压
    private int restingHeartRate; // 静息心率
    private LocalDateTime recordedAt;
}
