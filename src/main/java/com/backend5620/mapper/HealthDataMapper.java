package com.backend5620.mapper;

import com.backend5620.object.HealthData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HealthDataMapper {

    @Insert("INSERT INTO HealthData (userId, gender, birthDate, height, weight, cholesterol, " +
            "systolicBloodPressure, diastolicBloodPressure, restingHeartRate, recordedAt) " +
            "VALUES (#{userId}, #{gender}, #{birthDate}, #{height}, #{weight}, #{cholesterol}, " +
            "#{systolicBloodPressure}, #{diastolicBloodPressure}, #{restingHeartRate}, CURRENT_TIMESTAMP)")
    void insertHealthData(HealthData healthData);

    @Select("SELECT * FROM HealthData WHERE userId = #{userId} ORDER BY recorded_at DESC LIMIT 1")
    @Results({
            @Result(column="health_data_id", property="healthDataId"),

            @Result(column="userId", property="userId"),
            @Result(column="height", property="height"),
            @Result(column="weight", property="weight"),
            @Result(column="cholesterol", property="cholesterol"),
            @Result(column="systolicBloodPressure", property="systolicBloodPressure"),
            @Result(column="diastolicBloodPressure", property="diastolicBloodPressure"),
            @Result(column="restingHeartRate", property="restingHeartRate"),
            @Result(column="recordedAt", property="recordedAt")
    })
    HealthData getLatestHealthDataByUserId(int userId);

    @Select("SELECT * FROM HealthData WHERE userId = #{userId} ORDER BY recordedAt DESC")
    @Results({
            @Result(property = "healthDataId", column = "healthDataId"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "height", column = "height"),
            @Result(property = "weight", column = "weight"),
            @Result(property = "cholesterol", column = "cholesterol"),
            @Result(property = "systolicBloodPressure", column = "systolicBloodPressure"),
            @Result(property = "diastolicBloodPressure", column = "diastolicBloodPressure"),
            @Result(property = "restingHeartRate", column = "restingHeartRate"),
            @Result(property = "recordedAt", column = "recordedAt")
    })
    List<HealthData> getHistoricalHealthData(int userId);
}