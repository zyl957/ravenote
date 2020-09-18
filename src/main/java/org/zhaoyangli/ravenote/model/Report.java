package org.zhaoyangli.ravenote.model;

import lombok.Data;

@Data
public class Report {

   //auto increment id, used as the primary key of its corresponding table
   private int id;

   // the username of the initiator of the report
   private String username;

   // the id of the note this reporter reported
   private int noteId;

   // the URL to the corresponding page this note in on
   private String pageUrl;

   // the floor level of this note on that page, e.g. "2#"
   private int noteNumber;

   // the content of this report that the reporter wrote
   private String content;

}
