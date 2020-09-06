package org.zhaoyangli.ravenote.tools;

import org.springframework.stereotype.Service;
import org.zhaoyangli.ravenote.model.User;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserAccountTool {

    @Resource
    UserService userService;

    public void userValidationCheck(HttpServletRequest request,String username){

        UserAccount userAccount = (UserAccount) request.getSession().getAttribute("userAccount");
        User user = new User(username);
        userService.userValidationCheck(user, userAccount);
    }

}
