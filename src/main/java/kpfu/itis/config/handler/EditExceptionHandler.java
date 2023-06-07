package kpfu.itis.config.handler;

import kpfu.itis.dto.request.UserRegisterRequest;
import kpfu.itis.exception.validation.IncorrectPasswordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
@Slf4j
public class EditExceptionHandler {

    private static final String EDIT_JSP_PAGE = "edit";


    @ExceptionHandler(IncorrectPasswordException.class)
    public ModelAndView incorrect(IncorrectPasswordException e) {
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView(EDIT_JSP_PAGE);
        modelAndView.addObject("inc", Boolean.TRUE);
        return modelAndView;
    }

}
