package com.backend5620.service;


import com.backend5620.object.HealthData;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HealthDataService {

    void saveHealthData(HealthData healthData);

    HealthData getLatestHealthDataByUserId(int userId);

    List<HealthData> getHistoricalHealthData(int userId);
}
