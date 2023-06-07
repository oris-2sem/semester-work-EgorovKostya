package kpfu.itis.service.impl;

import kpfu.itis.model.GenreEntity;
import kpfu.itis.repository.GenreRepository;
import kpfu.itis.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    @Override
    public List<GenreEntity> getGenres() {
        return genreRepository.findAll();
    }
}
