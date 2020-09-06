package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhaoyangli.ravenote.model.Unit;

@Mapper
public interface UnitMapper {

    @Select("SELECT * FROM unit WHERE unit_id=#{unitId}")
    Unit getUnitByUnitId(@Param("unitId") String unitId);
}
