package com.backend5620.mapper;

import com.backend5620.object.DietRecords;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DietRecordsMapper {
    @Insert("INSERT INTO DietRecords (user_id, food_description, calories, recorded_at) " +
            "VALUES (#{userId}, #{foodDescription}, #{calories}, CURRENT_TIMESTAMP)")
    void insertDietRecord(DietRecords dietRecords);
    @Select("SELECT * FROM DietRecords WHERE user_id = #{userId}")
    DietRecords getDietRecordsByUserId(int userId);
}
