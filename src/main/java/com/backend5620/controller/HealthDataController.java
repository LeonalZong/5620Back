package com.backend5620.controller;

import com.backend5620.object.HealthData;
import com.backend5620.object.Result;
import com.backend5620.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthdata")
public class HealthDataController {

    @Autowired
    private HealthDataService healthDataService;

    @PostMapping("/save")
    public Result saveHealthData(@RequestBody HealthData healthData) {
        try {
            healthDataService.saveHealthData(healthData);
            return Result.success();
        } catch (Exception e) {
            return Result.error("Failed to save health data: " + e.getMessage());
        }
    }

    @GetMapping("/get/{userId}")
    public Result getHealthDataByUserId(@PathVariable int userId) {
        try {
            HealthData healthData = healthDataService.getHealthDataByUserId(userId);
            return Result.success(healthData);
        } catch (Exception e) {
            return Result.error("Failed to retrieve health data: " + e.getMessage());
        }

    }
}
