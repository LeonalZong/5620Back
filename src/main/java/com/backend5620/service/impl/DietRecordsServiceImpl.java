package com.backend5620.service.impl;

import com.backend5620.mapper.DietRecordsMapper;
import com.backend5620.object.DietRecords;
import com.backend5620.service.DietRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietRecordsServiceImpl implements DietRecordsService {
    @Autowired
    private DietRecordsMapper dietRecordsMapper;
    @Override
    public void saveDietRecords(DietRecords dietRecords) {
        dietRecordsMapper.insertDietRecord(dietRecords);
    }

    @Override
    public DietRecords getDietRecordsByUserId(int userId) {
        return dietRecordsMapper.getDietRecordsByUserId(userId);
    }
}
