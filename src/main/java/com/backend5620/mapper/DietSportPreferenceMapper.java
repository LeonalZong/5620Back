package com.backend5620.mapper;

import com.backend5620.object.DietSportPreference;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DietSportPreferenceMapper {
    @Insert("INSERT INTO DietSportPreferences (userId, dietPreferences, sportPreferences, updatedAt) " +
            "VALUES (#{userId}, #{dietPreferences}, #{sportPreferences}, CURRENT_TIMESTAMP)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertDietSportPreference(DietSportPreference dietSportPreference);

    @Select("SELECT * FROM DietSportPreferences WHERE userId = #{userId}")
    DietSportPreference getDietSportPreferenceByUserId(int userId);

    @Update("UPDATE DietSportPreferences SET dietPreferences = #{dietPreferences}, " +
            "sportPreferences = #{sportPreferences}, updatedAt = CURRENT_TIMESTAMP " +
            "WHERE userId = #{userId}")
    void updateDietSportPreference(DietSportPreference dietSportPreference);
}