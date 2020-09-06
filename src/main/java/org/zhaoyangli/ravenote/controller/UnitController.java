package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoyangli.ravenote.model.Lecture;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.LectureService;
import org.zhaoyangli.ravenote.service.UnitService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UnitController {

    @Resource
    private UnitService unitService;

    @Resource
    private LectureService lectureService;

    @GetMapping("/unit")
    public String Unit(@RequestParam("unitId") String unitId,
                         HttpServletRequest request,
                         Model model) {

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        unitService.unitValidationCheck(userAccount.getUsername(), unitId);

        List<Lecture> lectures = lectureService.getLecturesByUnitId(unitId);     //get all of the lectures this unit has
        model.addAttribute("unitId",unitId);
        model.addAttribute("lectures",lectures);
        return "unit";
    }
}
