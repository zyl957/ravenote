package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.*;
import org.zhaoyangli.ravenote.model.UserAccount;

import java.util.List;

@Mapper
public interface UserAccountMapper {

    @Insert("INSERT INTO useraccount (username,password,token,gmt_create,gmt_modified) VALUES (#{username},#{password},#{token},#{gmtCreate},#{gmtModified})")
    void insertNewAccount(UserAccount userAccount);

    @Delete("DELETE FROM useraccount WHERE username=#{username}")
    void deleteAccount(UserAccount userAccount);

    @Update("UPDATE useraccount SET password=#{password} WHERE username=#{username}")
    void updatePassword(UserAccount userAccount);

    @Update("UPDATE useraccount SET token=#{token} WHERE username=#{username}")
    void updateCookie(@Param("token") String token, @Param("username") String username);

    @Select("SELECT password FROM useraccount WHERE username=#{username}")
    String getPasswordByUsername(@Param("username") String username);

    @Select("SELECT * FROM useraccount WHERE token=#{token}")
    UserAccount getUserAccountByToken(@Param("token") String token);

    @Select("SELECT * FROM useraccount WHERE username=#{username}")
    UserAccount getUserAccountByUsername(@Param("username") String username);

    @Select("SELECT username FROM useraccount")
    List<String> getUsernames();
}
