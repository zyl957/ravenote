package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.CustomException;
import org.zhaoyangli.ravenote.model.Report;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.ReportService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Resource
    ReportService reportService;

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request){

        // The identity authentication before accessing the backstage page
        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        if (!userAccount.getUsername().equals("admin")){
            throw new CustomException(CustomErrorCodeEnum.HAVE_NO_ACCESS);
        }
        return "admin";
    }

    @RequestMapping("/admin/welcome")
    public String welcome(){
        return "adminWelcome";
    }

    @RequestMapping("/admin/report")
    public String report(Model model){

        List<Report> reports = reportService.getReports();
        model.addAttribute("reports",reports);
        return "adminReport";
    }

    // remove a report at the backstage
    @RequestMapping("/admin/report/remove")
    public String reportRemove(@RequestParam("reportId") int reportId){
        reportService.deleteReport(reportId);
        return "redirect:/admin/report";
    }

    @RequestMapping("/admin/course")
    public String course(){
        return "adminCourse";
    }
}
