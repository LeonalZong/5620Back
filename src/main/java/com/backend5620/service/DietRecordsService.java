package com.backend5620.service;


import com.backend5620.object.DietRecords;

public interface DietRecordsService {
    void saveDietRecords(DietRecords dietRecords);

    DietRecords getDietRecordsByUserId(int userId);
}
