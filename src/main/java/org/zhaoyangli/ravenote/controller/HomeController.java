package org.zhaoyangli.ravenote.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhaoyangli.ravenote.model.Unit;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.UnitService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Resource   //Matches firstly by name. @Autowired matches firstly by type
    private UnitService unitService;

    @RequestMapping({"/","/home","/index"})
    public String goHome(HttpServletRequest request,
                         Model model){

        UserAccount ua = (UserAccount) request.getSession().getAttribute("userAccount");

        if (ua != null) {
            String username = ua.getUsername();
            List<Unit> units = unitService.getUnitList(username);   //get all of the units this account linked with
            model.addAttribute("units", units);     //put them in the model and pass it to html file
        }

        return"home";
    }
}
