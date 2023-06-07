package kpfu.itis.service;

import kpfu.itis.dto.request.AuthorRequest;
import kpfu.itis.dto.response.AuthorResponse;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    List<AuthorResponse> getAuthors();

    AuthorResponse getAuthorById(UUID authorId);

    AuthorResponse createAuthor(AuthorRequest request);

    void deleteAuthorById(UUID authorId);

    AuthorResponse updateAuthorById(UUID authorId, AuthorRequest request);
}
