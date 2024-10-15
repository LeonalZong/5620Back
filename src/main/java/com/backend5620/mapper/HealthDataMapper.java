package com.backend5620.mapper;

import com.backend5620.object.HealthData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HealthDataMapper {

    @Insert("INSERT INTO HealthData (user_id, height, weight, cholesterol, blood_pressure, recorded_at) " +
            "VALUES (#{userId}, #{height}, #{weight}, #{cholesterol}, #{bloodPressure}, CURRENT_TIMESTAMP)")
    void insertHealthData(HealthData healthData);

    @Select("SELECT * FROM HealthData WHERE user_id = #{userId}")
    HealthData getHealthDataByUserId(int userId);
}
