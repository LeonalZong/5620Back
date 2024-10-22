package com.backend5620.mapper;

import com.backend5620.object.HealthReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HealthReportMapper {
    @Select("SELECT * FROM HealthReport WHERE userId = #{userId}")
    @Results({
            @Result(property = "reportId", column = "reportId"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "reportContent", column = "reportContent"),
            @Result(property = "createdAt", column = "createdAt")
    })
    HealthReport getHealthReportByUserId(int userId);

    @Select("SELECT * FROM HealthReport WHERE userId = #{userId}")
    @Results({
            @Result(property = "reportId", column = "reportId"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "reportContent", column = "reportContent"),
            @Result(property = "createdAt", column = "createdAt")
    })
    List<HealthReport> getAllHealthReportsByUserId(int userId);

    @Insert("INSERT INTO HealthReport (userId, report_content, created_at) VALUES (#{userId}, #{reportContent}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "reportId")
    void insertHealthReport(HealthReport healthReport);

    @Delete("DELETE FROM HealthReport WHERE reportId = #{reportId} AND userId = #{userId}")
    void deleteReport(@Param("reportId") int reportId, @Param("userId") int userId);
}