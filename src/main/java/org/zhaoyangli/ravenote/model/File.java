package org.zhaoyangli.ravenote.model;

import lombok.Data;

@Data
public class File {

    //auto increment id, used as the primary key of its corresponding table
    private int id;

    // id of the note which stores this file
    private int noteId;

    // local address of this file
    private String fileAddress;
}
