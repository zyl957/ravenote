package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

// a piece of information to record a collection behavior
@Data
public class Collection {

    //auto increment id, used as the primary key of its corresponding table
    private int id;

    // the username of the current user
    private String username;

    // the id of the note that the user collected
    private int noteId;

    // collection initiation time
    private Date gmtCreate;
}
