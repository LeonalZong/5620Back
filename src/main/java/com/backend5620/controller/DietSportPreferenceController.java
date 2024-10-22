package com.backend5620.controller;

import com.backend5620.object.DietSportPreference;
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
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        try {
            Claims claims = Jwtutils.parseJwt(token);
            int userId = (int) claims.get("id");  // 注意：这里使用 "id" 而不是 "userId"
            dietSportPreference.setUserId(userId);

            log.info("Saving diet and sport preference for user: {}", userId);
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