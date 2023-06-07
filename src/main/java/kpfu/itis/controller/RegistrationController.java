package kpfu.itis.controller;

import javax.validation.constraints.NotNull;
import kpfu.itis.api.RegistrationApi;
import kpfu.itis.dto.request.UserRegisterRequest;
import kpfu.itis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequiredArgsConstructor
public class RegistrationController implements RegistrationApi {

    private final UserService userService;

    @Override
    @PreAuthorize("permitAll()")
    public ModelAndView registerUser(@ModelAttribute("user") @NotNull UserRegisterRequest user) {
        userService.registerUser(user);
        return new ModelAndView("redirect:" + UriComponentsBuilder.fromPath("/signin").build());
    }

    @Override
    @PreAuthorize("permitAll()")
    public ModelAndView registerPage() {
        ModelAndView register = new ModelAndView("register");
        register.addObject("user", new UserRegisterRequest());
        return register;
    }
}
