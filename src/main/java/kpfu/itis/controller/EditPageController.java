package kpfu.itis.controller;

import kpfu.itis.api.EditPageApi;
import kpfu.itis.dto.request.PasswordUpdateRequest;
import kpfu.itis.dto.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class EditPageController implements EditPageApi {

    @Override
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getPage() {
        return new ModelAndView("edit")
                .addObject("user", new UserUpdateRequest())
                .addObject("pass", new PasswordUpdateRequest());
    }

}
