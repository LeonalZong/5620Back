package com.backend5620.mapper;

import com.backend5620.object.DietSportPreference;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DietSportPreferenceMapper {
    @Insert("INSERT INTO DietSportPreferences (user_id, diet_preferences, sport_preferences, updated_at) " +
            "VALUES (#{userId}, #{dietPreferences}, #{sportPreferences}, CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertDietSportPreference(DietSportPreference dietSportPreference);

    @Select("SELECT * FROM DietSportPreferences WHERE user_id = #{userId}")
    DietSportPreference getDietSportPreferenceByUserId(int userId);

    @Update("UPDATE DietSportPreferences SET diet_preferences = #{dietPreferences}, " +
            "sport_preferences = #{sportPreferences}, updated_at = CURRENT_TIMESTAMP " +
            "WHERE user_id = #{userId}")
    void updateDietSportPreference(DietSportPreference dietSportPreference);
}