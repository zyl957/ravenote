package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.CustomException;
import org.zhaoyangli.ravenote.exception.ExceptionJsonObj;
import org.zhaoyangli.ravenote.model.User;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.UserAccountService;
import org.zhaoyangli.ravenote.service.UserService;
import org.zhaoyangli.ravenote.service.UserUnitService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class SignUpController {

    @Resource
    UserAccountService userAccountService;

    @Resource
    UserService userService;

    @Resource
    UserUnitService userUnitService;

    @GetMapping(value = "/signup")
    public String signUp(){
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUpCheck(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        Date date = new Date();
        UserAccount userAccount = new UserAccount(username,password,null,date,date);
        userAccountService.insertNewAccount(userAccount);
        User user = new User(username);
        userService.insertNewUser(user);

        //temporarily involving all users in the existing two units
        userUnitService.insertUserUnit(username);

        return "signin";
    }

    @RequestMapping("/signup/set_profile")
    public String setProfile(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        if (username == null){
            throw new CustomException(CustomErrorCodeEnum.HAVE_NO_ACCESS);
        }
        request.getSession().removeAttribute("username");
        return "profile";
    }

    @ResponseBody
    @PostMapping(value = "/signup/username_check",produces = "application/json")
    public ExceptionJsonObj usernameCheck(@RequestParam("username") String username){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");  // a pattern that contains characters other than numbers and letters
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()){    // if matcher matched invalid characters
            return new ExceptionJsonObj(CustomErrorCodeEnum.INVALID_CHARACTERS);
        }
        if (username.length()<5 || username.length()>10){
            return new ExceptionJsonObj(CustomErrorCodeEnum.INVALID_USERNAME_LENGTH);
        }
        List<String> names = userAccountService.getUsernames();
        String lowerUsername = username.toLowerCase();  //because the database is not case sensitive, so process it to avoid duplicates
        if (names.contains(lowerUsername)){
            return new ExceptionJsonObj(CustomErrorCodeEnum.DUPLICATE_USERNAME);
        }

        return new ExceptionJsonObj(CustomErrorCodeEnum.OPERATION_SUCCESS);
    }

    @ResponseBody
    @PostMapping(value = "/signup/password_check",produces = "application/json")
    public ExceptionJsonObj passwordCheck(@RequestParam("password") String password){

        if (password.length()<6 || password.length()>16){
            return new ExceptionJsonObj(CustomErrorCodeEnum.INVALID_PASSWORD_LENGTH);
        }

        return new ExceptionJsonObj(CustomErrorCodeEnum.OPERATION_SUCCESS);
    }

}
