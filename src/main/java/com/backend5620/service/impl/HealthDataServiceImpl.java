package com.backend5620.service.impl;

import com.backend5620.mapper.HealthDataMapper;
import com.backend5620.object.HealthData;
import com.backend5620.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HealthDataServiceImpl implements HealthDataService {

    @Autowired
    private HealthDataMapper healthDataMapper;

    @Override
    @Transactional
    public void saveHealthData(HealthData healthData) {
        healthDataMapper.insertHealthData(healthData);
    }

    @Override
    public HealthData getLatestHealthDataByUserId(int userId) {
        return healthDataMapper.getLatestHealthDataByUserId(userId);
    }
}
