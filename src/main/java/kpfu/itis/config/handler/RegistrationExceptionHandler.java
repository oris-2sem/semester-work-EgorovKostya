package kpfu.itis.config.handler;

import kotlin.io.AccessDeniedException;
import kpfu.itis.dto.request.UserRegisterRequest;
import kpfu.itis.exception.validation.DifferentPasswordException;
import kpfu.itis.exception.database.UsernameAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestControllerAdvice
public class RegistrationExceptionHandler {

    private static final String REGISTRATION_JSP_PAGE = "register";

    @ExceptionHandler(DifferentPasswordException.class)
    public ModelAndView differentPassword(DifferentPasswordException e) {
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView(REGISTRATION_JSP_PAGE);
        modelAndView.addObject("user", new UserRegisterRequest());
        modelAndView.addObject("username", e.getUsername());
        modelAndView.addObject("diffPassword", Boolean.TRUE);
        return modelAndView;
    }


    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ModelAndView incorrectUsername(UsernameAlreadyExistsException e) {
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView(REGISTRATION_JSP_PAGE);
        modelAndView.addObject("user", new UserRegisterRequest());
        modelAndView.addObject("username", e.getMessage());
        modelAndView.addObject("incorrectUsername", Boolean.TRUE);
        return modelAndView;
    }


}
