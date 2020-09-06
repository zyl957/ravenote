package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

@Data
public class Notification {

    private int id;

    private int targetNoteId;

    private String targetNoteTitle;

    private String sender;

    private String receiver;

    private int status;

    private Date gmtCreate;

}
