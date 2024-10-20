package com.backend5620.mapper;

import com.backend5620.object.HealthReport;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HealthReportMapper {
    @Insert("INSERT INTO HealthReport (user_id, report_content, created_at) VALUES (#{userId}, #{reportContent}, CURRENT_TIMESTAMP)")
    void insertHealthReport(HealthReport healthReport);
    @Select("SELECT * FROM HealthReport WHERE user_id = #{userId}")
    @Results({
            @Result(property = "reportId", column = "report_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "reportContent", column = "report_content"),
            @Result(property = "createdAt", column = "created_at")
    })
    HealthReport getHealthReportByUserId(int userId);

}
