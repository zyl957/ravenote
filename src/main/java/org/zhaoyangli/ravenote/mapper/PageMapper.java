package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhaoyangli.ravenote.model.Page;

import java.util.List;

@Mapper
public interface PageMapper {

    @Select("SELECT * FROM page WHERE unit_id=#{unitId} and lecture_id=#{lectureId}")
    List<Page> getAllPages(@Param("unitId") String unitId,
                           @Param("lectureId") String lectureId);

    @Select("SELECT * FROM page WHERE unit_id=#{unitId} and lecture_id=#{lectureId} and slide_id=#{slideId}")
    Page getPageByThreeIds(@Param("unitId") String unitId,
                           @Param("lectureId") String lectureId,
                           @Param("slideId") String slideId);

    @Select("SELECT * FROM page WHERE id=#{pageId}")
    Page getPageById(@Param("pageId") int pageId);

    @Insert("INSERT INTO page (unit_id,lecture_id,slide_id,slide_url) VALUES (#{unitId},#{lectureId},#{slideId},#{slideUrl})")
    void insertPage(@Param("unitId") String unitId,
                    @Param("lectureId") String lectureId,
                    @Param("slideId") int slideId,
                    @Param("slideUrl") String slideUrl);

    @Select("SELECT slide_url FROM page WHERE id=#{pageId}")
    String getSlideUrlById(@Param("pageId") int pageId);
}
