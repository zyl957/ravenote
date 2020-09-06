package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhaoyangli.ravenote.model.User;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Resource
    private UserService userService;

    @RequestMapping("/profile")
    public String profile(@RequestParam("username") String username,
                          Model model){

        User user = userService.getUserByUsername(username);
        model.addAttribute("user",user);
        return "profile";
    }

    @PostMapping("/profile")
    public String editProfile(@ModelAttribute(value = "user") User user,
                              HttpServletRequest request){

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        userService.userValidationCheck(user, userAccount);
        userService.updateUser(user);
        return "redirect:/profile?username=" + user.getUsername();
    }
}
