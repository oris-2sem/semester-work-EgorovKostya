package kpfu.itis.controller;

import kpfu.itis.api.UserApi;
import kpfu.itis.dto.request.PasswordUpdateRequest;
import kpfu.itis.dto.request.UserUpdateRequest;
import kpfu.itis.model.TitleEntity;
import kpfu.itis.model.UserEntity;
import kpfu.itis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    @PreAuthorize("permitAll()")
    public ModelAndView getUserInfo(UUID userId) {
        ModelAndView modelAndView = new ModelAndView("profile");
        List<Map.Entry<TitleEntity, String>> titles = userService.getTitlesByUserId(userId);
        UserEntity user = userService.getUserById(userId);
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("user", user);
        modelAndView.addObject("created", user.getCreatedAt());
        modelAndView.addObject("usersFavourites", titles);
        return modelAndView;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addTitle(UUID userId, UUID titleId) {
        userService.addTitle(userId, titleId);
        return new ModelAndView("redirect:" + UriComponentsBuilder.fromPath("/titles").build());
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteTitle(UUID userId, UUID titleId) {
        userService.deleteTitle(userId, titleId);
        return new ModelAndView("redirect:" +
                UriComponentsBuilder.fromPath("/users/" + userId).build());
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ModelAndView updateUser(UUID userId, UserUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("edit");
        }
        userService.updateUserById(userId, request);
        return new ModelAndView("redirect:" +
                UriComponentsBuilder.fromPath("/edit").build());
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ModelAndView updatePassword(UUID userId, PasswordUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("edit");
        }
        userService.updatePasswordById(userId, request);
        return new ModelAndView("redirect:" +
                UriComponentsBuilder.fromPath("/edit").build());
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ModelAndView deleteUserById(UUID userId) {
        userService.deleteUserById(userId);
        return new ModelAndView("redirect:" +
                UriComponentsBuilder.fromPath("/").build());
    }
}
