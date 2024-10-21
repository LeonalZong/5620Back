package com.backend5620.controller;

import com.backend5620.object.DietSportPreference;
import com.backend5620.object.HealthData;
import com.backend5620.object.Result;
import com.backend5620.pojo.Jwtutils;
import com.backend5620.service.DietSportPreferenceService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.backend5620.object.DietSportPreference.*;

@Slf4j
@RestController
@RequestMapping("/dietsportpreference")
public class DietSportPreferenceController {

    @Autowired
    private DietSportPreferenceService dietSportPreferenceService;

    @PostMapping("/save")
    public Result saveDietSportPreference(@RequestBody DietSportPreference dietSportPreference, @RequestHeader("Authorization") String token) {
        log.info("Received save request for diet sport preference");
        try {
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            Claims claims = Jwtutils.parseJwt(token);
            int userId = (int) claims.get("id");
            dietSportPreference.setUserId(userId);

            dietSportPreferenceService.saveDietSportPreference(dietSportPreference);
            log.info("Diet sport preference saved successfully for user ID: {}", userId);


            return Result.success("保存成功");
        } catch (Exception e) {
            log.error("Error saving diet sport preference", e);
            return Result.error("保存失败: " + e.getMessage());
        }
    }


    @GetMapping("/get/{userId}")
//    public Result getDietSportPreferenceByUserId(@PathVariable int userId) {
//        try {
//            DietSportPreference dietSportPreference = dietSportPreferenceService.getDietSportPreferenceByUserId(userId);
//            return Result.success(dietSportPreference);
//        } catch (Exception e) {
//            log.error("Failed to get diet and sport preference", e);
//            return Result.error("Failed to get diet and sport preference: " + e.getMessage());
//        }
//    }
    public Result getDietSportPreferenceByUserId(@RequestHeader("Authorization") String token) {
        try {
            // 移除 "Bearer " 前缀（如果有）
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // 解析 JWT，获取用户ID
            Claims claims = Jwtutils.parseJwt(token);
            int userId = (int) claims.get("id");

            log.info("get health data by userId: {}", userId);
            DietSportPreference dietSportPreference = dietSportPreferenceService.getDietSportPreferenceByUserId(userId);
            return Result.success(dietSportPreference);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error("Failed to retrieve health data: " + e.getMessage());
        }
    }
}