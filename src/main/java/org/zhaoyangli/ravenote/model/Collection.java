package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

@Data
public class Collection {

    private int id;

    private String username;

    private int noteId;

    private Date gmtCreate;
}
