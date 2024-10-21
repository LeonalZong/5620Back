package com.backend5620.mapper;

import com.backend5620.object.HealthData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HealthDataMapper {

    @Insert("INSERT INTO HealthData (userId, height, weight, cholesterol, systolic_blood_pressure, diastolic_blood_pressure, resting_heart_rate, recorded_at) " +
            "VALUES (#{userId}, #{height}, #{weight}, #{cholesterol}, #{systolicBloodPressure}, #{diastolicBloodPressure}, #{restingHeartRate}, CURRENT_TIMESTAMP)")
    void insertHealthData(HealthData healthData);

    @Select("SELECT * FROM HealthData WHERE userId = #{userId} ORDER BY recorded_at DESC LIMIT 1")
    @Results({
            @Result(column="health_data_id", property="healthDataId"),

            @Result(column="userId", property="userId"),
            @Result(column="height", property="height"),
            @Result(column="weight", property="weight"),
            @Result(column="cholesterol", property="cholesterol"),
            @Result(column="systolic_blood_pressure", property="systolicBloodPressure"),
            @Result(column="diastolic_blood_pressure", property="diastolicBloodPressure"),
            @Result(column="resting_heart_rate", property="restingHeartRate"),
            @Result(column="recorded_at", property="recordedAt")
    })
    HealthData getLatestHealthDataByUserId(int userId);

}