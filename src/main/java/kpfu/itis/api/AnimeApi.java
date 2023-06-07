package kpfu.itis.api;


import kpfu.itis.dto.request.AnimeSearchWithFilterRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/animes")
public interface AnimeApi {

    @GetMapping
    ModelAndView getAnimeSearchPage(
            AnimeSearchWithFilterRequest request);

    @GetMapping("/{animeId}")
    ModelAndView getAnimeById(@PathVariable String animeId);

    @GetMapping("/watch/{animeId}")
    ModelAndView getAnimeEpisodes(@PathVariable String animeId);
}
