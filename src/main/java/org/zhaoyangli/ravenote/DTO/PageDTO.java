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

    private String slideUrl;

    private List<Note> notes;

}
