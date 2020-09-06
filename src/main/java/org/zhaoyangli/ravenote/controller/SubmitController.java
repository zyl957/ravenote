package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.ExceptionJsonObj;
import org.zhaoyangli.ravenote.model.Note;
import org.zhaoyangli.ravenote.model.Notification;
import org.zhaoyangli.ravenote.model.Page;
import org.zhaoyangli.ravenote.service.NoteService;
import org.zhaoyangli.ravenote.service.NotificationService;
import org.zhaoyangli.ravenote.service.PageService;

import javax.annotation.Resource;
import java.util.Date;

@Controller
public class SubmitController {

    @Resource
    private PageService pageService;

    @Resource
    private NoteService noteService;

    @Resource
    private NotificationService notificationService;

    @ResponseBody
    @PostMapping(value = "/submit", consumes = "application/json",produces = "application/json")
    public ExceptionJsonObj submit(@RequestBody Note tempNote){
        Date date = new Date();
        tempNote.setGmtCreate(date);
        tempNote.setGmtModified(date);

        if (tempNote.getTitle().equals("")){
            tempNote.setTitle(null);
            notificationService.createNotification(tempNote,date);
        }
        noteService.InsertNewNote(tempNote);

        return new ExceptionJsonObj(CustomErrorCodeEnum.OPERATION_SUCCESS);
    }

    @PostMapping("/submit/edit")
    public String submitEdit(@ModelAttribute(value="note") Note note){
        Date date = new Date();
        note.setGmtModified(date);
        if (note.getTitle()!=null){
            noteService.updateNote(note);
            Page page = pageService.getPageById(note.getPageId());
            return "redirect:/lecture?unitId="+page.getUnitId()+"&lectureId="+page.getLectureId()+"&slideId="+page.getSlideId();
        } else{
            noteService.updateReply(note);
            return "redirect:/note?noteId="+note.getParentId();
        }
    }


}
