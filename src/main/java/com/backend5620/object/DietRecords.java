package com.backend5620.object;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietRecords {

    private int dietId;
    private int userId;
    private String foodDescription;
    private double calories;
    private String recordedAt;
}
