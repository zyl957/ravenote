package org.zhaoyangli.ravenote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zhaoyangli.ravenote.DTO.SignInDTO;
import org.zhaoyangli.ravenote.exception.CustomErrorCodeEnum;
import org.zhaoyangli.ravenote.exception.ExceptionJsonObj;
import org.zhaoyangli.ravenote.model.UserAccount;
import org.zhaoyangli.ravenote.service.UserAccountService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
public class UserAccountController {

    @Resource
    private UserAccountService userAccountService;

    @RequestMapping("/signin")
    public String signInPage(){
        return "signin";
    }

    @ResponseBody
    @RequestMapping(
            value="/signin",
            method=RequestMethod.POST,
            consumes="application/json")
    public ExceptionJsonObj signIn(@RequestBody SignInDTO signInDTO,
                                   HttpServletRequest request,
                                   HttpServletResponse response){
            String inputUsername = signInDTO.getUsername();
            String inputPassword = signInDTO.getPassword();
            //retrieve password in the database
            String password = userAccountService.getPasswordByUsername(inputUsername);
            if (password==null){
                return new ExceptionJsonObj(CustomErrorCodeEnum.WRONG_USERNAME_OR_PASSWORD);
            }
            if(password.equals(inputPassword)){
                String token = UUID.randomUUID().toString();
                userAccountService.updateCookie(token,inputUsername); //create and store a token in the database for quick sign-in via cookie
                Date date = new Date();
                UserAccount ua = new UserAccount(inputUsername,password,token,date,date);
                request.getSession().setAttribute("userAccount",ua);

                //cookie generation
                Cookie cookie = new Cookie("token",token);
                cookie.setMaxAge(4*60*60);   //life: 4 hours
                cookie.setDomain("localhost");
                cookie.setPath("/");
                response.addCookie(cookie);

                //use redirect to go to HomeController again, if not, browser will directly visit home.html
                return new ExceptionJsonObj(CustomErrorCodeEnum.OPERATION_SUCCESS);
            }else{
                return new ExceptionJsonObj(CustomErrorCodeEnum.WRONG_USERNAME_OR_PASSWORD);
            }
    }


    @RequestMapping("/fail")
    public String signInFailed(HttpServletRequest request,
                               HttpServletResponse response){

        //remove userAccount object in the session if it exists
        if (request.getSession().getAttribute("userAccount") != null) {
            request.getSession().removeAttribute("userAccount");
        }

        //create a new cookie to replace the old one when users sign out
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);    //same as deletion
        cookie.setPath("/");
        response.addCookie(cookie);
        return "home";
    }

    @ResponseBody
    @PostMapping(value = "/getSessionUserAccount", consumes = "application/json",produces = "application/json")
    public UserAccount getSessionUserAccount(HttpServletRequest request){
        return (UserAccount) request.getSession().getAttribute("userAccount");
    }
}
