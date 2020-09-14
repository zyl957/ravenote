package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.*;
import org.zhaoyangli.ravenote.model.Note;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Insert("INSERT INTO note (page_id,username,title,content,gmt_create,gmt_modified,parent_id,visibility) VALUES (#{pageId},#{username},#{title},#{content},#{gmtCreate},#{gmtModified},#{parentId},#{visibility})")
    void InsertNewNote(Note note);

    @Select("SELECT * FROM note WHERE page_id=#{pageId}")
    List<Note> getNotesByPageId(@Param("pageId") int pageId);

    @Select("SELECT * FROM note WHERE id=#{noteId}")
    Note getNoteById(@Param("noteId") int noteId);

    @Select("SELECT * FROM note WHERE parent_id=#{parentId}")
    List<Note> getNotesByParentId(@Param("parentId") int parentId);

    @Select("SELECT * FROM note WHERE username=#{username}")
    List<Note> getNotesByUsername(@Param("username") String username);

    @Update("UPDATE note SET title=#{title}, content=#{content}, gmt_modified=#{gmtModified} WHERE id=#{id}")
    void updateNote(Note note);

    @Update("UPDATE note SET content=#{content}, gmt_modified=#{gmtModified} WHERE id=#{id}")
    void updateReply(Note reply);

    @Delete("DELETE FROM note WHERE id=#{noteId}")
    void deleteNoteById(@Param("noteId") int noteId);

    @Delete("DELETE FROM note WHERE parent_id=#{parentId}")
    void deleteNotesByParentId(@Param("parentId") int parentId);

    @Update("UPDATE note SET visibility=#{visibility} WHERE id=#{noteId}")
    void updateVisibility(@Param("noteId") int noteId,
                          @Param("visibility") int visibility);
}
