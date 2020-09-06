package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhaoyangli.ravenote.model.Lecture;

import java.util.List;

@Mapper
public interface LectureMapper {

    @Select("select * from lecture where unit_id=#{unitId}")
    List<Lecture> getLecturesByUnitId(@Param("unitId") String unitId);

    @Select("select * from lecture where unit_id=#{unitId} and lecture_id=#{lectureId}")
    Lecture getLecture(@Param("unitId") String unitId, @Param("lectureId") String lectureId);

}
