package kpfu.itis.converter;

import kpfu.itis.model.AnimeEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonToAnimeEntity implements Converter<JSONObject, AnimeEntity> {

    @Override
    public AnimeEntity convert(JSONObject anime) {
        Long id = anime.getLong("id");
        String code = anime.getString("code");
        JSONObject names = anime.getJSONObject("names");
        String ruName = names.getString("ru");
        String enName = names.getString("en");
        JSONObject posters = anime.getJSONObject("posters");
        JSONObject poster = posters.getJSONObject("small");
        String imageUrl = poster.getString("url");
        String description;
        if (!anime.isNull("description")) {
            description = anime.getString("description");
        } else {
            description = "Описание отсутствует";
        }
        JSONObject season = anime.getJSONObject("season");
        String released = String.valueOf(season.getInt("year"));
        JSONObject pses = anime.getJSONObject("status");
        String status;
        if (!pses.isNull("string")) {
            status = pses.getString("string");
        } else {
            status = "Без статуса";
        }
        JSONObject type = anime.getJSONObject("type");
        Integer duration;
        if (!type.isNull("length")) {
            duration = type.getInt("length");
        } else {
            duration = 0;
        }
        Integer episodes;
        if (!type.isNull("episodes")) {
            episodes = type.getInt("episodes");
        } else {
            episodes = 0;
        }
        String fullString;
        if (!type.isNull("full_string")) {
            fullString = type.getString("full_string");
        } else {
            fullString = "";
        }
        Integer favorites = anime.getInt("in_favorites");
        List<String> animeGenres = new ArrayList<>();
        JSONArray genres = anime.getJSONArray("genres");
        genres.toList().forEach(x -> {
            animeGenres.add((String) x);
        });
        return AnimeEntity.builder()
                .id(id)
                .code(code)
                .description(description)
                .ruName(ruName)
                .enName(enName)
                .fullString(fullString)
                .episodes(episodes)
                .duration(duration)
                .released(released)
                .genres(animeGenres)
                .favourites(favorites)
                .status(status)
                .imageUrl("https://www.anilibria.tv" + imageUrl)
                .build();
    }
}
