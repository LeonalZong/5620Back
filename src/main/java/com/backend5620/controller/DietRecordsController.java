package com.backend5620.controller;


import com.backend5620.object.DietRecords;
import com.backend5620.object.Result;
import com.backend5620.service.DietRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dietrecords")
public class DietRecordsController {

    @Autowired
    private DietRecordsService dietRecordsService;

    @PostMapping("/save")
    public Result saveDietRecord(@RequestBody DietRecords dietRecords) {
        try {
            dietRecordsService.saveDietRecords(dietRecords);
            return Result.success();
        } catch (Exception e) {
            return Result.error("Failed to save diet record: " + e.getMessage());
        }
    }

    @GetMapping("/get/{userId}")
    public Result getDietRecordsByUserId(@PathVariable int userId) {
        try {
            DietRecords dietRecords = dietRecordsService.getDietRecordsByUserId(userId);
            return Result.success(dietRecords);
        } catch (Exception e) {
            return Result.error("Failed to get diet records: " + e.getMessage());
        }
    }
}
