package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.*;
import org.zhaoyangli.ravenote.DTO.CollectionDTO;
import org.zhaoyangli.ravenote.model.Collection;

import java.util.List;

@Mapper
public interface CollectionMapper {

    @Insert("INSERT INTO collection (username, note_id, gmt_create) VALUES (#{username}, #{noteId}, #{gmtCreate})")
    void insertCollection(CollectionDTO collectionDTO);

    @Select("SELECT * FROM collection WHERE username = #{username}")
    List<Collection> getCollectionsByDTO(CollectionDTO collectionDTO);

    @Select("SELECT * FROM collection WHERE username = #{username}")
    List<Collection> getCollectionsByUsername(@Param("username") String username);

    @Delete("DELETE FROM collection WHERE username = #{username} and note_id = #{noteId}")
    void removeFromCollection(CollectionDTO collectionDTO);
}
