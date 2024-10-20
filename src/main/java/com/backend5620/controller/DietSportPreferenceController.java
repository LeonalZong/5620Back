package com.backend5620.controller;

import com.backend5620.object.DietSportPreference;
import com.backend5620.object.Result;
import com.backend5620.service.DietSportPreferenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dietsportpreference")
public class DietSportPreferenceController {

    @Autowired
    private DietSportPreferenceService dietSportPreferenceService;

    @PostMapping("/save")
    public Result saveDietSportPreference(@RequestBody DietSportPreference dietSportPreference) {
        try {
            log.info("Saving diet and sport preference");
            dietSportPreferenceService.saveDietSportPreference(dietSportPreference);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to save diet and sport preference", e);
            return Result.error("Failed to save diet and sport preference: " + e.getMessage());
        }
    }

    @GetMapping("/get/{userId}")
    public Result getDietSportPreferenceByUserId(@PathVariable int userId) {
        try {
            DietSportPreference dietSportPreference = dietSportPreferenceService.getDietSportPreferenceByUserId(userId);
            return Result.success(dietSportPreference);
        } catch (Exception e) {
            log.error("Failed to get diet and sport preference", e);
            return Result.error("Failed to get diet and sport preference: " + e.getMessage());
        }
    }
}