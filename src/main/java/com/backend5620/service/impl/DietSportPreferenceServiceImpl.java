package com.backend5620.service.impl;

import com.backend5620.mapper.DietSportPreferenceMapper;
import com.backend5620.object.DietSportPreference;
import com.backend5620.service.DietSportPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietSportPreferenceServiceImpl implements DietSportPreferenceService {

    @Autowired
    private DietSportPreferenceMapper dietSportPreferenceMapper;

    @Override
    public void saveDietSportPreference(DietSportPreference dietSportPreference) {
        DietSportPreference existingPreference = dietSportPreferenceMapper.getDietSportPreferenceByUserId(dietSportPreference.getUserId());
        if (existingPreference == null) {
            dietSportPreferenceMapper.insertDietSportPreference(dietSportPreference);
        } else {
            dietSportPreferenceMapper.updateDietSportPreference(dietSportPreference);
        }
    }

    @Override
    public DietSportPreference getDietSportPreferenceByUserId(int userId) {
        return dietSportPreferenceMapper.getDietSportPreferenceByUserId(userId);
    }
}