package kpfu.itis.service.impl;

import kpfu.itis.dto.request.AuthorRequest;
import kpfu.itis.dto.response.AuthorResponse;
import kpfu.itis.exception.database.AuthorAlreadyExistsException;
import kpfu.itis.exception.database.AuthorNotFoundException;
import kpfu.itis.mapper.AuthorMapper;
import kpfu.itis.model.AuthorEntity;
import kpfu.itis.repository.AuthorRepository;
import kpfu.itis.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;
    @Override
    public List<AuthorResponse> getAuthors() {
        return authorMapper.toListResponse(authorRepository.findAll());
    }

    @Override
    public AuthorResponse getAuthorById(UUID authorId) {
        AuthorEntity authorEntity = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(
                        String.format("Автор с таким id не найден: %s", authorId)));
        return authorMapper.toResponse(authorEntity);
    }

    @Override
    public AuthorResponse createAuthor(AuthorRequest request) {
        if (authorRepository.existsByName(request.getName())) {
            throw new AuthorAlreadyExistsException(
                    String.format("Автор с таким названием уже существует %s", request.getName()));
        }
        AuthorEntity author = authorRepository.save(authorMapper.toEntity(request));
        return authorMapper.toResponse(author);
    }

    @Override
    public void deleteAuthorById(UUID authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    public AuthorResponse updateAuthorById(UUID authorId, AuthorRequest request) {
        AuthorEntity authorEntity = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(
                        String.format("Автор с таким id не найден: %s", authorId)));
        authorEntity.setAge(request.getAge());
        authorEntity.setName(request.getName());
        return authorMapper.toResponse(authorEntity);
    }
}
