package com.backend5620.mapper;

import com.backend5620.object.HealthReport;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HealthReportMapper {

    @Insert("INSERT INTO HealthReport (userId, reportContent, createdAt) VALUES (#{userId}, #{reportContent}, CURRENT_TIMESTAMP)")
    void insertHealthReport(HealthReport healthReport);

    @Select("SELECT * FROM HealthReport WHERE userId = #{userId}")
    @Results({
            @Result(property = "reportId", column = "report_id"),
            @Result(property = "userId", column = "userId"),  // 注意这里使用 userId 而不是 user_id
            @Result(property = "reportContent", column = "report_content"),
            @Result(property = "createdAt", column = "created_at")
    })
    HealthReport getHealthReportByUserId(int userId);

}
