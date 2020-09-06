package org.zhaoyangli.ravenote.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CollectionDTO {

    private String username;

    private int noteId;

    private Boolean isCollect;

    private Date gmtCreate;

}
