package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.*;
import org.zhaoyangli.ravenote.model.User;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username=#{username}")
    User getUserByUsername(@Param("username") String username);

    @Update("Update user SET position=#{position}, first_name=#{firstName}, last_name=#{lastName}, email=#{email}, department=#{department}, course=#{course}, level=#{level} WHERE username=#{username}")
    void updateUser(User user);

    @Insert("INSERT INTO user (username) VALUES (#{username})")
    void insertNewUser(User user);
}
