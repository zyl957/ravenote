package org.zhaoyangli.ravenote.DTO;

import lombok.Data;
import org.zhaoyangli.ravenote.model.Note;

import java.util.List;


//

@Data
public class PageDTO {

    private int id;

    private String unitId;

    private String lectureId;

    private int slideId;

    // the URL to the slide image on the local server
    private String slideUrl;

    // all of the notes this page contains
    private List<Note> notes;

}
