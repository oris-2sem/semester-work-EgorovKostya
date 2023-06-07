package kpfu.itis.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public interface LoginApi {
    @GetMapping("/signin")
    ModelAndView getLoginPage();
}
