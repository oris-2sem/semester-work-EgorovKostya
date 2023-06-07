package kpfu.itis.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public interface HomePageApi {

    @GetMapping
    ModelAndView getHomePage();
}
