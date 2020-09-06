package org.zhaoyangli.ravenote.mapper;

import org.apache.ibatis.annotations.*;
import org.zhaoyangli.ravenote.model.Notification;

import java.util.List;

@Mapper
public interface NotificationMapper {

    @Insert("INSERT INTO notification (target_note_id,target_note_title,sender,receiver,status,gmt_create) VALUES (#{targetNoteId},#{targetNoteTitle},#{sender},#{receiver},#{status},#{gmtCreate})")
    void insertNotification(Notification notification);

    @Select("SELECT * FROM notification WHERE receiver=#{username}")
    List<Notification> getNotificationByReceiver(@Param("username") String username);

    @Update("UPDATE notification SET status=#{status} WHERE id=#{id}")
    void updateStatusById(@Param("status") int status,
                          @Param("id") int id);

    @Update("UPDATE notification SET status=#{status} WHERE receiver=#{receiver}")
    void updateStatusByReceiver(@Param("status") int status,
                                @Param("receiver")String receiver);

    @Select("SELECT COUNT(*) FROM notification WHERE receiver=#{receiver} and status=0")
    int getNotisByUnread(String receiver);
}
