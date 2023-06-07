package kpfu.itis.api;

import kpfu.itis.dto.request.PasswordUpdateRequest;
import kpfu.itis.dto.request.UserUpdateRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/users")
public interface UserApi {

    @GetMapping("{userId}")
    ModelAndView getUserInfo(@PathVariable UUID userId);

    @PatchMapping("{userId}/titles/{titleId}")
    ModelAndView addTitle(@PathVariable UUID userId, @PathVariable UUID titleId);

    @DeleteMapping("{userId}/titles/{titleId}")
    ModelAndView deleteTitle(@PathVariable UUID userId, @PathVariable UUID titleId);

    @PutMapping("/{userId}")
    ModelAndView updateUser(@PathVariable UUID userId, @Valid @ModelAttribute(name = "user") UserUpdateRequest updateRequest, BindingResult result);

    @PostMapping("/{userId}")
    ModelAndView updatePassword(@PathVariable UUID userId, @Valid @ModelAttribute(name = "pass") PasswordUpdateRequest request, BindingResult result);

    @DeleteMapping("/{userId}")
    ModelAndView deleteUserById(@PathVariable UUID userId);
}
