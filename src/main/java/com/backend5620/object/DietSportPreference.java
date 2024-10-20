package com.backend5620.object;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietSportPreference {
    private int id;
    private int userId;
    private String dietPreferences;
    private String sportPreferences;
    private LocalDateTime updatedAt;
}