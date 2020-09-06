package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserUnitMapper {

    @Select("SELECT unit_id FROM userunit WHERE username=#{username}")
    List<String> getUnitIdByUsername(@Param("username") String username);

    @Insert("INSERT INTO userunit (username,unit_id) VALUES (#{username},#{unitId})")
    void insertUserUnit(@Param("username") String username,
                        @Param("unitId") String unitId);
}
