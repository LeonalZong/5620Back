package com.backend5620.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthData {
    private int healthDataId;
    private int userId;
    private double height;
    private double weight;
    private double cholesterol;
    private String bloodPressure;
    private String recordedAt;
}
