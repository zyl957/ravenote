package org.zhaoyangli.ravenote.model;

import lombok.Data;

// a lecture contains many different pages according to the number of the slides
// a page contains a slide and its corresponding notes
@Data
public class Page {

    //auto increment id, used as the primary key of its corresponding table
    private int id;

    // the id of the unit this page belongs to, e.g. "CM50109"
    private String unitId;

    // the serial number of the lecture this page belongs to, e.g. "1A"
    private String lectureId;

    // the id of the slide this page contains
    private int slideId;

    // the URL of the local address of this slide
    private String slideUrl;

}
