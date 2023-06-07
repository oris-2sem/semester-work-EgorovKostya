package kpfu.itis.controller;

import kpfu.itis.api.CommentApi;
import kpfu.itis.dto.request.CommentRequest;
import kpfu.itis.service.TitleService;
import kpfu.itis.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommentController implements CommentApi {

    private final TitleService titleService;

    @Override
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addComment(UUID titleId, String value) {
        titleService.addComment(titleId, value, ContextHolder.getUserFromSecurityContext().getUsername());
        return new ModelAndView("redirect:" + UriComponentsBuilder.fromPath("/titles/" + titleId).build());
    }
}
