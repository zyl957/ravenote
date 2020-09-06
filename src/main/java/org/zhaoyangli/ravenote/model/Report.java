package org.zhaoyangli.ravenote.model;

import lombok.Data;

@Data
public class Report {

   private int id;

   private String username;

   private int noteId;

   private String pageUrl;

   private int noteNumber;

   private String content;

}
