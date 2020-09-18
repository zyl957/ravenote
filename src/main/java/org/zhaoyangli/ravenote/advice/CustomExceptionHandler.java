package org.zhaoyangli.ravenote.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.zhaoyangli.ravenote.exception.CustomException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice   // a @Component to define @ExceptionHandler, applies to all methods using @RequestMapping
public class CustomExceptionHandler {

    // handles all exceptions within the Exception class and its subclasses thrown by the Controller layer
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable ex, HttpServletRequest request, Model model) {

        if (ex instanceof CustomException){     // if this throwable object is a customException object defined in the exception package
            model.addAttribute("message", ex.getMessage());     // let error page get the corresponding exception message
        }else {
            ex.printStackTrace();
        }

        return new ModelAndView("error");   // go to the error page
    }
}
