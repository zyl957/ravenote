package org.zhaoyangli.ravenote.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class MyReplyDTO {

    private int id;

    // id of its parent note
    private int parentId;

    // title of its parent note
    private String parentTitle;

    // content of this reply
    private String content;

    private Date gmtCreate;

    private Date gmtModified;

    public MyReplyDTO(int id, int parentId, String parentTitle, String content, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.parentId = parentId;
        this.parentTitle = parentTitle;
        this.content = content;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
