package com.backend5620.service;


import com.backend5620.object.HealthData;
import org.springframework.stereotype.Service;


public interface HealthDataService {

    void saveHealthData(HealthData healthData);

    HealthData getHealthDataByUserId(int userId);
}
