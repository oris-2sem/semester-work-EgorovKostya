package kpfu.itis.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/redact")
public interface RedactorPageApi {

    @GetMapping
    ModelAndView getRedactorPage();


}
