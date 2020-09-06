package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoyangli.ravenote.DTO.LectureDTO;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.LectureService;
import org.zhaoyangli.ravenote.service.UnitService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LectureController {

    @Resource
    private LectureService lectureService;

    @Resource
    private UnitService unitService;


    @GetMapping("/lecture")
    public String Lecture(@RequestParam("unitId") String unitId,
                          @RequestParam("lectureId") String lectureId,
                          @RequestParam("slideId") int slideId,
                          HttpServletRequest request,
                          Model model){

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        unitService.unitValidationCheck(userAccount.getUsername(), unitId);

        LectureDTO lectureDTO = lectureService.getLectureDTO(unitId,lectureId);
        model.addAttribute("lectureDTO",lectureDTO);
        model.addAttribute("slideId", slideId);
        return "lecture";
    }
}
