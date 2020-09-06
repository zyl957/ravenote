package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

@Data
public class Note {

    private int id;

    private int pageId;

    private String username;

    private String title;

    private String content;

    private Date gmtCreate;

    private Date gmtModified;

    private int mark;

    private int parentId;

}
