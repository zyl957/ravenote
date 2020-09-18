package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

// a piece of information to record a notification behavior
@Data
public class Notification {

    //auto increment id, used as the primary key of its corresponding table
    private int id;

    // the id of the note that has a new reply
    private int targetNoteId;

    // the title of the note
    private String targetNoteTitle;

    // the username of the user which created the new reply
    private String sender;

    // the username of the user that receives this notification
    private String receiver;

    // 0: unread 1: read
    private int status;

    // the time of the notification is created
    private Date gmtCreate;

}
