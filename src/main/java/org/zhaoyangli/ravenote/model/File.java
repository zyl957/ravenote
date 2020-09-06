package org.zhaoyangli.ravenote.model;

import lombok.Data;

@Data
public class File {

    private int id;

    private int noteId;

    private String fileAddress;
}
