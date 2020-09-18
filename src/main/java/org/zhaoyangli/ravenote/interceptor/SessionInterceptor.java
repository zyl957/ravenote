package org.zhaoyangli.ravenote.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zhaoyangli.ravenote.mapper.UserAccountMapper;
import org.zhaoyangli.ravenote.model.UserAccount;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Resource
    private UserAccountMapper userAccountMapper;

    // execute before every pages load
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Use session and cookie to keep signing in
        UserAccount ua;
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return true;
        }
        for(Cookie c : cookies){
            if(c.getName().equals("token")){    //find the cookie named "token"
                String token = c.getValue();
                ua = userAccountMapper.getUserAccountByToken(token);
                if (ua != null){
                    request.getSession().setAttribute("userAccount",ua);    //find an account with the stored token, if it exists, put it in the session
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
