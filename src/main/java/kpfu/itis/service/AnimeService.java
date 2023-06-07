package kpfu.itis.service;

import kpfu.itis.dto.request.AnimeSearchWithFilterRequest;
import kpfu.itis.model.AnimeEntity;

import java.util.List;

public interface AnimeService {
    List<AnimeEntity> getAnimesByFilter(AnimeSearchWithFilterRequest request);

    AnimeEntity getAnimeById(String animeId);
}
