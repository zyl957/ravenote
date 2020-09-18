package org.zhaoyangli.ravenote.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.DTO.LectureDTO;
import org.zhaoyangli.ravenote.DTO.PageDTO;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.CustomException;
import org.zhaoyangli.ravenote.mapper.LectureMapper;
import org.zhaoyangli.ravenote.mapper.NoteMapper;
import org.zhaoyangli.ravenote.mapper.PageMapper;
import org.zhaoyangli.ravenote.mapper.UnitMapper;
import org.zhaoyangli.ravenote.model.Lecture;
import org.zhaoyangli.ravenote.model.Note;
import org.zhaoyangli.ravenote.model.Page;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LectureService {

    @Resource
    private LectureMapper lectureMapper;

    @Resource
    private PageMapper pageMapper;

    @Resource
    private NoteMapper noteMapper;

    @Resource
    private UnitMapper unitMapper;


    public LectureDTO getLectureDTO(String unitId, String lectureId){

        // retrieving the lecture in the database, and return corresponding errors if the unit or the lecture cannot be found
        Lecture lecture = lectureMapper.getLecture(unitId,lectureId);
        if (lecture==null){
            if (unitMapper.getUnitByUnitId(unitId)==null){
                throw new CustomException(CustomErrorCodeEnum.UNIT_NOT_FOUND);
            } else{
                throw new CustomException(CustomErrorCodeEnum.LECTURE_NOT_FOUND);
            }
        }

        LectureDTO lectureDTO = new LectureDTO();
        List<PageDTO> pageDTOList = new ArrayList<>();
        BeanUtils.copyProperties(lecture, lectureDTO);  // extract the information in the lecture object to its DTO
        List<Page> pages = pageMapper.getAllPages(unitId,lectureId);    // get all the pages of this lecture

        // add note objects into pageDTOs, and add pageDTOs into the lecture DTO
        for(Page page:pages){
            PageDTO pageDTO = new PageDTO();
            BeanUtils.copyProperties(page, pageDTO);
            List<Note> notes = noteMapper.getNotesByPageId(pageDTO.getId());
            pageDTO.setNotes(notes);
            pageDTOList.add(pageDTO);
        }

        lectureDTO.setNumOfPages(pages.size());
        lectureDTO.setPageDTOS(pageDTOList);

        return lectureDTO;
    }

    public List<Lecture> getLecturesByUnitId(String unitId) {
        return lectureMapper.getLecturesByUnitId(unitId);
    }
}
