package com.backend5620.mapper;

import com.backend5620.object.HealthData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HealthDataMapper {

    @Insert("INSERT INTO HealthData (userId, height, weight, cholesterol, blood_pressure, resting_heart_rate, recorded_at) " +
            "VALUES (#{userId}, #{height}, #{weight}, #{cholesterol}, #{bloodPressure}, #{restingHeartRate}, CURRENT_TIMESTAMP)")
    void insertHealthData(HealthData healthData);

    @Select("SELECT * FROM HealthData WHERE userId = #{userId} LIMIT 1")
    HealthData getHealthDataByUserId(int userId);
}
