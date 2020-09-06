package org.zhaoyangli.ravenote.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.zhaoyangli.ravenote.exception.CustomException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable ex, HttpServletRequest request, Model model) {
        if (ex instanceof CustomException){
            model.addAttribute("message", ex.getMessage());
        }else {
            ex.printStackTrace();
        }

        return new ModelAndView("error");
    }
}
