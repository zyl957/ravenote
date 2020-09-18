package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

@Data
public class Lecture {

    //auto increment id, used as the primary key of its corresponding table
    private int id;

    // the id of the unit this lecture belongs to, e.g. "CM50109"
    private String unitId;

    // the serial number of this lecture e.g. "1A"
    private String lectureId;

    // the title of this lecture
    private String lectureTitle;

    // yyyy-MM-dd
    private Date date;

    // the URL to the recording of this lecture
    private String videoUrl;

}
