package com.backend5620.controller;

import com.backend5620.object.HealthData;
import com.backend5620.object.Result;
import com.backend5620.pojo.Jwtutils;
import com.backend5620.service.HealthDataService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthdata")
@Slf4j
public class HealthDataController {

    @Autowired
    private HealthDataService healthDataService;

    @PostMapping("/save")
    public Result saveHealthData(@RequestBody HealthData healthData, @RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 解析 JWT，获取用户ID
        Claims claims = Jwtutils.parseJwt(token);
        int userId = (int) claims.get("id");
        healthData.setUserId(userId);

        try {
            log.info("save health data: {}", healthData);
            healthDataService.saveHealthData(healthData);
            return Result.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error("Failed to save health data: " + e.getMessage());
        }
    }

    @GetMapping("/get/{userId}")
    public Result getHealthDataByUserId(@RequestHeader("Authorization") String token) {
        try {
            // 移除 "Bearer " 前缀（如果有）
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // 解析 JWT，获取用户ID
            Claims claims = Jwtutils.parseJwt(token);
            int userId = (int) claims.get("id");

            log.info("get health data by userId: {}", userId);
            HealthData healthData = healthDataService.getHealthDataByUserId(userId);
            return Result.success(healthData);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error("Failed to retrieve health data: " + e.getMessage());
        }
    }


}
