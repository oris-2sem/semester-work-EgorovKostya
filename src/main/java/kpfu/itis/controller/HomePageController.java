package kpfu.itis.controller;

import kpfu.itis.api.HomePageApi;
import kpfu.itis.model.TitleEntity;
import kpfu.itis.model.UserEntity;
import kpfu.itis.service.TitleService;
import kpfu.itis.service.UserService;
import kpfu.itis.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HomePageController implements HomePageApi {

    @Override
    @PreAuthorize("permitAll()")
    public ModelAndView getHomePage() {
        return new ModelAndView("main");

    }
}
