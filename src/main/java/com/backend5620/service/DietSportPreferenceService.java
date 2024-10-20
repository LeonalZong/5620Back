package com.backend5620.service;

import com.backend5620.object.DietSportPreference;

public interface DietSportPreferenceService {
    void saveDietSportPreference(DietSportPreference dietSportPreference);
    DietSportPreference getDietSportPreferenceByUserId(int userId);
}