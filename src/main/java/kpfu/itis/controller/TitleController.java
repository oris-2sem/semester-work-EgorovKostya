package kpfu.itis.controller;

import kpfu.itis.api.TitleApi;
import kpfu.itis.dto.request.CommentRequest;
import kpfu.itis.dto.request.TitleRegistrationRequest;
import kpfu.itis.model.TitleEntity;
import kpfu.itis.model.UserEntity;
import kpfu.itis.service.TitleService;
import kpfu.itis.service.UserService;
import kpfu.itis.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
public class TitleController implements TitleApi {

    private final TitleService titleService;

    private final UserService userService;

    @Override
    @PreAuthorize("permitAll()")
    public ModelAndView getTitlesByName(String name, Integer page, Integer size) {
        ModelAndView modelAndView = new ModelAndView("search");
        List<Map.Entry<TitleEntity, String>> titles = titleService.findByName(name, PageRequest.of(page, size));

        modelAndView.addObject("titles", titles);
        UserEntity user = null;
        if (ContextHolder.getUserFromSecurityContext() != null) {
            user = userService.getById(ContextHolder.getUserFromSecurityContext().getId());
        }
        modelAndView.addObject("user", user);
        modelAndView.addObject("page", page);
        modelAndView.addObject("size", size);
        modelAndView.addObject("name", name);
        return modelAndView;
    }

    @Override
    @PreAuthorize("permitAll()")
    public ModelAndView getTitle(UUID titleId) {
        ModelAndView modelAndView = new ModelAndView("title");
        Map.Entry<TitleEntity, String> title = titleService.getTitleById(titleId);
        modelAndView.addObject("title", title);
        return modelAndView;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView deleteTitle(UUID titleId) {
        titleService.deleteById(titleId);
        return new ModelAndView("redirect:" + UriComponentsBuilder.fromPath("/titles").build());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView addTitle(TitleRegistrationRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("redact");
        }
        titleService.save(request);
        return new ModelAndView("redirect:" + UriComponentsBuilder.fromPath("redact").build());
    }

    @Override
    public TitleEntity getPopular() {
        return titleService.getPopular();
    }

}
