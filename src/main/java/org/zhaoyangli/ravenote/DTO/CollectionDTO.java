package org.zhaoyangli.ravenote.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CollectionDTO {

    // current user
    private String username;

    // corresponding id of the note
    private int noteId;

    // true: this operation is "add to collection"  false: "remove from collection"
    private Boolean isCollect;

    // operation initiation time
    private Date gmtCreate;

}
