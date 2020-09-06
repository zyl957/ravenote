package org.zhaoyangli.ravenote.model;

import lombok.Data;

import java.util.Date;

@Data
public class Lecture {

    private int id;

    private String unitId;

    private String lectureId;

    private String lectureTitle;

    // yyyy-MM-dd
    private Date date;

    private String videoUrl;

}
