package kpfu.itis.api;

import kpfu.itis.dto.request.UserRegisterRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public interface RegistrationApi {

    @PostMapping("/register")
    ModelAndView registerUser(@Valid UserRegisterRequest user);

    @GetMapping("/register")
    ModelAndView registerPage();
}
