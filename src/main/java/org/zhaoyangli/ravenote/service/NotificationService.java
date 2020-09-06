package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhaoyangli.ravenote.mapper.NoteMapper;
import org.zhaoyangli.ravenote.mapper.NotificationMapper;
import org.zhaoyangli.ravenote.model.Note;
import org.zhaoyangli.ravenote.model.Notification;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    @Resource
    NotificationMapper notificationMapper;

    @Resource
    NoteMapper noteMapper;

    @Transactional
    public void createNotification(Note reply, Date date) {
        Notification notification = new Notification();
        Note note = noteMapper.getNoteById(reply.getParentId());
        notification.setStatus(0);
        notification.setTargetNoteId(reply.getParentId());
        notification.setTargetNoteTitle(note.getTitle());
        notification.setSender(reply.getUsername());
        notification.setReceiver(note.getUsername());
        notification.setGmtCreate(date);
        notificationMapper.insertNotification(notification);
    }

    public List<Notification> getNotificationByReceiver(String username) {
        return notificationMapper.getNotificationByReceiver(username);
    }

    public void updateStatusById(int status, int id) {
        notificationMapper.updateStatusById(status,id);
    }

    public void updateStatusByReceiver(int status, String receiver) {
        notificationMapper.updateStatusByReceiver(status,receiver);
    }

    public int getNotisByUnread(String receiver) {
        return notificationMapper.getNotisByUnread(receiver);
    }
}
