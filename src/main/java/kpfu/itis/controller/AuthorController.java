package kpfu.itis.controller;

import kpfu.itis.api.AuthorApi;
import kpfu.itis.dto.request.AuthorRequest;
import kpfu.itis.dto.response.AuthorResponse;
import kpfu.itis.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {

    private final AuthorService authorService;
    @Override
    public List<AuthorResponse> getAuthors() {
        return authorService.getAuthors();
    }

    @Override
    public AuthorResponse getAuthorById(UUID authorId) {
        return authorService.getAuthorById(authorId);
    }

    @Override
    public AuthorResponse createAuthor(AuthorRequest request) {
        return authorService.createAuthor(request);
    }

    @Override
    public void deleteAuthorById(UUID authorId) {
        authorService.deleteAuthorById(authorId);
    }

    @Override
    public AuthorResponse updateAuthorById(UUID authorId, AuthorRequest request) {
        return authorService.updateAuthorById(authorId, request);
    }
}
