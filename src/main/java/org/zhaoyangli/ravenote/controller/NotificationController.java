package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.ExceptionJsonObj;
import org.zhaoyangli.ravenote.model.Notification;
import org.zhaoyangli.ravenote.model.User;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.NotificationService;
import org.zhaoyangli.ravenote.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
public class NotificationController {

    @Resource
    private UserService userService;

    @Resource
    private NotificationService notificationService;

    @RequestMapping("/notification")
    public String notification(@RequestParam("username") String username,
                               HttpServletRequest request,
                               Model model){

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        User user = new User(username);
        userService.userValidationCheck(user, userAccount);

        List<Notification> notifications = notificationService.getNotificationByReceiver(username);
        Collections.reverse(notifications);     // Reverse the chronological order to let newer notifications get displayed earlier
        model.addAttribute("notifications",notifications);

        return "notification";
    }

    //mark a notification as read according to its id
    @ResponseBody
    @PostMapping(value = "/notification/read", produces = "application/json")
    public ExceptionJsonObj read(@RequestParam("id") int id){

        notificationService.updateStatusById(1,id);

        return new ExceptionJsonObj(CustomErrorCodeEnum.OPERATION_SUCCESS) ;
    }

    //mark all notifications as read according to the username of the current user
    @ResponseBody
    @PostMapping(value = "/notification/allRead", produces = "application/json")
    public ExceptionJsonObj allRead(@RequestParam("receiver") String receiver){

        notificationService.updateStatusByReceiver(1,receiver);

        return new ExceptionJsonObj(CustomErrorCodeEnum.OPERATION_SUCCESS) ;
    }

    //get all unread notifications according to the current user
    @ResponseBody
    @PostMapping(value = "/notification/unreadNumber")
    public int unreadNumber(@RequestParam("receiver") String receiver){

        return notificationService.getNotisByUnread(receiver);

    }
}
