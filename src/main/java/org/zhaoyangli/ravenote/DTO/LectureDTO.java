package org.zhaoyangli.ravenote.DTO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LectureDTO {

    private int id;

    private String unitId;

    private String lectureId;

    private String lectureTitle;

    // YYYY-MM-DD
    private Date date;

    private String videoUrl;


    // the number of pages a lecture has
    private int numOfPages;

    private List<PageDTO> pageDTOS;
}
