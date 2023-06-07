package kpfu.itis.controller;

import kpfu.itis.api.LoginApi;
import kpfu.itis.dto.request.LoginFormRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequiredArgsConstructor
public class LoginController implements LoginApi {


    @Override
    @PreAuthorize("permitAll()")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView("signin");
        modelAndView.addObject("user", new LoginFormRequest());
        return modelAndView;
    }
}
