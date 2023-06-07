package kpfu.itis.api;

import kpfu.itis.dto.request.UserUpdateRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/edit")
public interface EditPageApi {

    @GetMapping
    ModelAndView getPage();

}
