package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

// the class for notes and replies
@Data
public class Note {

    //auto increment id, used as the primary key of its corresponding table
    private int id;

    // the id of the page this note/reply is on
    private int pageId;

    // the username of the creator
    private String username;

    // the title of the note. if is reply, value = null
    private String title;

    // the content of this note/reply
    private String content;

    // the timestamp this note/reply is created
    private Date gmtCreate;

    // the timestamp this note/reply is modified
    private Date gmtModified;

    // 0: private 1: public 2: protected (in the future release)
    private int visibility;

    // the id of the parent note of this reply. if is note, value = 0
    private int parentId;
}
