package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zhaoyangli.ravenote.DTO.CollectionDTO;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.ExceptionJsonObj;
import org.zhaoyangli.ravenote.model.*;
import org.zhaoyangli.ravenote.service.CollectionService;
import org.zhaoyangli.ravenote.service.NoteService;
import org.zhaoyangli.ravenote.service.PageService;
import org.zhaoyangli.ravenote.service.ReportService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class NoteController {

    @Resource
    private NoteService noteService;

    @Resource
    private PageService pageService;

    @Resource
    private CollectionService collectionService;

    @Resource
    private ReportService reportService;

    @RequestMapping("/note")
    public String notePage(@RequestParam("noteId") int noteId,
                           Model model){

        Note note = noteService.getNoteById(noteId);
        Page page = pageService.getPageById(note.getPageId());
        List<Note> replies = noteService.getNotesByParentId(noteId);
        model.addAttribute("page",page);
        model.addAttribute("note",note);
        model.addAttribute("replies",replies);
        return "/note";
    }

    @RequestMapping("/edit")
    public String editNote(@RequestParam("noteId") int noteId,
                           HttpServletRequest request,
                           Model model){

        Note note = noteService.getNoteById(noteId, request);
        Page page = pageService.getPageById(note.getPageId());
        model.addAttribute("page",page);
        model.addAttribute("note", note);
        return "/edit";
    }

    @RequestMapping("/delete")
    public String deleteNote(@RequestParam("noteId") int noteId,
                             HttpServletRequest request){

        Note note = noteService.getNoteById(noteId, request);
        noteService.deleteNotes(noteId);
        if (note.getTitle()!=null){
            Page page = pageService.getPageById(note.getPageId());
            return "redirect:/lecture?unitId="+page.getUnitId()+"&lectureId="+page.getLectureId()+"&slideId="+page.getSlideId();
        }
        else{
            return "redirect:/note?noteId="+note.getParentId();
        }
    }

    @ResponseBody
    @PostMapping(value = "/collection/operation", consumes = "application/json",produces = "application/json")
    public ExceptionJsonObj CollectNotes(@RequestBody CollectionDTO collectionDTO){
        if (collectionDTO.getIsCollect()){
            collectionDTO.setGmtCreate(new Date());
            collectionService.insertCollection(collectionDTO);
        }
        else {
            collectionService.removeFromCollection(collectionDTO);
        }
        return new ExceptionJsonObj(CustomErrorCodeEnum.OPERATION_SUCCESS);
    }

    @ResponseBody
    @PostMapping(value = "/collection",consumes = "application/json",produces = "application/json")
    public List<Integer> checkCollection(HttpServletRequest request){
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        List<Collection> collections = collectionService.getCollectionsByUsername(userAccount.getUsername());
        List<Integer> collectedIds = new ArrayList<>();
        for (Collection collection : collections){
            collectedIds.add(collection.getNoteId());
        }
        return collectedIds;
    }

    @ResponseBody
    @PostMapping(value = "/report", consumes = "application/json",produces = "application/json")
    public ExceptionJsonObj reportNote(@RequestBody Report report){
        reportService.insertReport(report);
        return new ExceptionJsonObj(CustomErrorCodeEnum.OPERATION_SUCCESS);
    }
}
