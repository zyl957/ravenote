package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.*;
import org.zhaoyangli.ravenote.model.Report;

import java.util.List;

@Mapper
public interface ReportMapper {

    @Insert("INSERT INTO report (username,note_id,page_url,note_number,content) VALUES (#{username},#{noteId},#{pageUrl},#{noteNumber},#{content})")
    void insertReport(Report report);

    @Select("SELECT * FROM report")
    List<Report> getReports();

    @Delete("DELETE FROM report WHERE id = #{reportId}")
    void deleteReport(@Param("reportId") int reportId);
}
