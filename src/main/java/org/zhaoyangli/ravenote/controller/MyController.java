package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoyangli.ravenote.DTO.MyNoteDTO;
import org.zhaoyangli.ravenote.DTO.MyReplyDTO;
import org.zhaoyangli.ravenote.model.*;
import org.zhaoyangli.ravenote.service.*;
import org.zhaoyangli.ravenote.tools.UserAccountTool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MyController {

    @Resource
    UserAccountTool userAccountTool;

    @Resource
    PageService pageService;

    @Resource
    private NoteService noteService;

    @Resource
    private CollectionService collectionService;

    @RequestMapping("/my-notes")
    public String myNotes(@RequestParam("username") String username,
                          HttpServletRequest request,
                          Model model){

        userAccountTool.userValidationCheck(request, username);

        // Combine some required attributes of the model note and page by iteration
        List<Note> notes = noteService.getNotesByUsername(username);
        List<MyNoteDTO> myNoteDTOS = new ArrayList<>();
        for (Note note : notes){
            if (note.getTitle()==null){
                continue;
            }
            Page page = pageService.getPageById(note.getPageId());
            MyNoteDTO myNoteDTO = new MyNoteDTO(note.getId(), note.getTitle(), note.getUsername(),note.getGmtCreate(), note.getGmtModified(),
                    page.getUnitId(), page.getLectureId(),page.getSlideId());
            myNoteDTOS.add(myNoteDTO);
        }

        Collections.reverse(myNoteDTOS);
        model.addAttribute("myNoteDTOS",myNoteDTOS);

        return "myNotes";
    }

    @RequestMapping("/my-replies")
    public String myReplies(@RequestParam("username") String username,
                          HttpServletRequest request,
                          Model model){

        userAccountTool.userValidationCheck(request, username);

        List<Note> notes = noteService.getNotesByUsername(username);
        List<MyReplyDTO> myReplyDTOS = new ArrayList<>();
        for (Note reply : notes){
            if (reply.getTitle()!=null){
                continue;
            }
            Note parentNote = noteService.getNoteById(reply.getParentId());
            MyReplyDTO myReplyDTO = new MyReplyDTO(reply.getId(), reply.getParentId(), parentNote.getTitle(), reply.getContent(), reply.getGmtCreate(), reply.getGmtModified());
            myReplyDTOS.add(myReplyDTO);
        }
        Collections.reverse(myReplyDTOS);
        model.addAttribute("myReplyDTOS",myReplyDTOS);

        return "myReplies";
    }

    @RequestMapping("/my-collections")
    public String myCollections(@RequestParam("username") String username,
                                HttpServletRequest request,
                                Model model){

        userAccountTool.userValidationCheck(request, username);

        List<Collection> collections = collectionService.getCollectionsByUsername(username);
        List<MyNoteDTO> myNoteDTOS = new ArrayList<>();
        for (Collection collection : collections){
            Note note = noteService.getNoteById(collection.getNoteId());
            Page page = pageService.getPageById(note.getPageId());
            MyNoteDTO myNoteDTO = new MyNoteDTO(note.getId(), note.getTitle(), note.getUsername(),collection.getGmtCreate(), null,
                    page.getUnitId(), page.getLectureId(),page.getSlideId());
            myNoteDTOS.add(myNoteDTO);
        }
        Collections.reverse(myNoteDTOS);
        model.addAttribute("myNoteDTOS",myNoteDTOS);
        return "myCollections";
    }

}
