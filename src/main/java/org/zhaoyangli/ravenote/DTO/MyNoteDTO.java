package org.zhaoyangli.ravenote.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * the DTO for "My Note" button related logic
 * stores attributes of some of a note and some of its corresponding page
 */

@Data
public class MyNoteDTO {

    private int id;

    // note title
    private String title;

    // current user
    private String username;

    // Note creation time
    private Date gmtCreate;

    // Note modification time
    private Date gmtModified;

    // information of the page which this note is on
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
