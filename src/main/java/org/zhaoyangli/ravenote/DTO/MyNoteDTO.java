package org.zhaoyangli.ravenote.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MyNoteDTO {

    private int id;

    private String title;

    private String username;

    private Date gmtCreate;

    private Date gmtModified;

    private String unitId;

    private String lectureId;

    private int slideId;

    public MyNoteDTO(int id, String title, String username, Date gmtCreate, Date gmtModified, String unitId, String lectureId, int slideId) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.unitId = unitId;
        this.lectureId = lectureId;
        this.slideId = slideId;
    }
}
