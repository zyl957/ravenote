package org.zhaoyangli.ravenote.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class MyReplyDTO {

    private int id;

    private int parentId;

    private String parentTitle;

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
