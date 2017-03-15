package com.spring.web;


import com.spring.exception.DAOException;
import com.spring.exception.InsufficientPermissionsException;
import com.spring.model.User;
import com.spring.model.enums.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({InsufficientPermissionsException.class, DAOException.class})
    public ModelAndView permissionsException(RuntimeException e){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView model = new ModelAndView("error");
        if (user.getUserAuthorities() == Role.ROLE_WAITER){
            model.addObject("url", "/waiter/tables");
        }else {
            model.addObject("url", "/admin/main");
        }
        model.addObject("error", e.getMessage());
        return model;
    }

}
