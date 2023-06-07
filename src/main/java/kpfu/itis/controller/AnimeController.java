package kpfu.itis.controller;

import kpfu.itis.api.AnimeApi;
import kpfu.itis.dto.request.AnimeSearchWithFilterRequest;
import kpfu.itis.model.AnimeEntity;
import kpfu.itis.model.GenreEntity;
import kpfu.itis.service.AnimeService;
import kpfu.itis.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnimeController implements AnimeApi {

    private final AnimeService animeService;

    private final GenreService genreService;

    @Override
    public ModelAndView getAnimeSearchPage(AnimeSearchWithFilterRequest request) {
        List<AnimeEntity> animes = animeService.getAnimesByFilter(request);
        ModelAndView view = new ModelAndView("anime-list");
        List<GenreEntity> genres = genreService.getGenres();
        view.addObject("animes", animes);
        view.addObject("genres", genres);
        view.addObject("request", request);
        view.addObject("animeFilter", new AnimeSearchWithFilterRequest());
        return view;
    }

    @Override
    public ModelAndView getAnimeById(String animeId) {
        AnimeEntity anime = animeService.getAnimeById(animeId);
        ModelAndView modelAndView = new ModelAndView("anime");
        modelAndView.addObject("anime", anime);
        return modelAndView;
    }

    @Override
    @PreAuthorize("permitAll()")
    public ModelAndView getAnimeEpisodes(String animeId) {
        return new ModelAndView("video")
                .addObject("animeId", animeId);
    }
}
