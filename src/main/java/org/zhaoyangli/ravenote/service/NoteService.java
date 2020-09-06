package org.zhaoyangli.ravenote.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.CustomException;
import org.zhaoyangli.ravenote.mapper.NoteMapper;
import org.zhaoyangli.ravenote.mapper.ReportMapper;
import org.zhaoyangli.ravenote.model.Note;
import org.zhaoyangli.ravenote.model.UserAccount;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class NoteService {

    @Resource
    private NoteMapper noteMapper;

    public void deleteNotes(int noteId){

        noteMapper.deleteNoteById(noteId);
        noteMapper.deleteNotesByParentId(noteId);

    }

    public Note getNoteById(int noteId) {
        Note note = noteMapper.getNoteById(noteId);
        if (note==null){
            throw new CustomException(CustomErrorCodeEnum.NOTE_NOT_FOUND);
        }
        return note;
    }

    public Note getNoteById(int noteId, HttpServletRequest request) {
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        Note note = noteMapper.getNoteById(noteId);
        if (note==null){
            throw new CustomException(CustomErrorCodeEnum.NOTE_NOT_FOUND);
        }
        else if (!note.getUsername().equals(userAccount.getUsername()) && !userAccount.getUsername().equals("admin")){
            throw new CustomException(CustomErrorCodeEnum.HAVE_NO_ACCESS);
        }
        return note;
    }

    public List<Note> getNotesByParentId(int noteId) {
        List<Note> replies = noteMapper.getNotesByParentId(noteId);
        if (replies == null){
            Note note = noteMapper.getNoteById(noteId);
            if (note == null){
                throw new CustomException(CustomErrorCodeEnum.NOTE_NOT_FOUND);
            }
        }
        return replies;
    }

    public void InsertNewNote(Note note) {
        noteMapper.InsertNewNote(note);
    }

    public List<Note> getNotesByUsername(String username) {
        return noteMapper.getNotesByUsername(username);
    }

    public void updateNote(Note note) {
        noteMapper.updateNote(note);
    }

    public void updateReply(Note note) {
        noteMapper.updateReply(note);
    }
}
