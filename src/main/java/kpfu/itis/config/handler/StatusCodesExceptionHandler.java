package kpfu.itis.config.handler;

import kotlin.io.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class StatusCodesExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ModelAndView denied(AccessDeniedException e) {
        log.error(e.getMessage());
        return new ModelAndView("redirect:" +
                UriComponentsBuilder.fromPath("/signin").build());
    }

        @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoHandlerFoundException(HttpServletRequest req, NoHandlerFoundException exception){
        log.error(exception.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", HttpStatus.NOT_FOUND.value());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("message", exception.getMessage());
        mav.addObject("stackTrace", exception.getStackTrace());
        mav.setViewName("errore/default");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ModelAndView onAllExceptions(HttpServletRequest req, Exception exception) {
        log.error(exception.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("message", exception.getMessage());
        mav.addObject("stackTrace", exception.getStackTrace());
        mav.setViewName("errore/default");
        return mav;
    }

}
