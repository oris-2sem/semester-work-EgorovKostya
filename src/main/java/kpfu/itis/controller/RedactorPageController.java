package kpfu.itis.controller;

import kpfu.itis.api.RedactorPageApi;
import kpfu.itis.dto.request.TitleRegistrationRequest;
import kpfu.itis.dto.response.AuthorResponse;
import kpfu.itis.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RedactorPageController implements RedactorPageApi {

    private final AuthorService authorService;
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView getRedactorPage() {

        List<AuthorResponse> authors = authorService.getAuthors();
        return new ModelAndView("redact")
                .addObject("authors", authors)
                .addObject("title", new TitleRegistrationRequest());
    }
}
