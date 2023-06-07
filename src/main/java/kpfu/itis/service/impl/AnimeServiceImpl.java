package kpfu.itis.service.impl;

import kpfu.itis.converter.JsonToAnimeEntity;
import kpfu.itis.dto.request.AnimeSearchWithFilterRequest;
import kpfu.itis.model.AnimeEntity;
import kpfu.itis.service.AnimeService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {

    private static final String GET_JSON_ANIME_INFO_URL_WITH_FILTERS = "http://api.anilibria.tv/v3/title/search/advanced?query=%s&filter=id,code,names,posters,type,genres,description,in_favorites,season,status&items_per_page=200";
    private static final String GET_JSON_ANIME_INFO_URL_BY_NAME = "http://api.anilibria.tv/v3/title/search?search=%s&filter=id,code,names,posters,type,genres,description,in_favorites,season,status&items_per_page=200";
    private static final String GET_JSON_ANIME_INFO_URL_BY_ID = "http://api.anilibria.tv/v3/title/search/advanced?simple_query=id==%s&filter=id,code,names,posters,type,genres,description,in_favorites,season,status&items_per_page=200";

    private final JsonToAnimeEntity jsonToAnimeEntity;

    @Override
    public List<AnimeEntity> getAnimesByFilter(AnimeSearchWithFilterRequest request) {
        String response = getResponseFromAPI(request, GET_JSON_ANIME_INFO_URL_WITH_FILTERS);
        if (response == null) {
            return null;
        }
        return getAnimeEntitiesFromString(response);
    }

    private String getResponseFromAPI(AnimeSearchWithFilterRequest request, String url) {
        OkHttpClient client = new OkHttpClient();
        String upUrl = changeUrl(request, url);
        System.out.println(upUrl);
        if (upUrl == null) {
            return null;
        }
        Request req = new Request.Builder()
                .url(upUrl)
                .build();
        try (Response response = client.newCall(req).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String changeUrl(AnimeSearchWithFilterRequest request, String url) {
        StringBuilder req = new StringBuilder();
        System.out.println(request);
        boolean valuePresent = false;
        for (Field f : request.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (getFieldValue(f, request) != null && !getFieldValue(f, request).equals("")) {
                valuePresent = true;
                switch (f.getName()) {
                    case "name":
                        String fromAPI = getResponseFromAPI(request.getName(), GET_JSON_ANIME_INFO_URL_BY_NAME);

                        List<AnimeEntity> animes = getAnimeEntitiesFromString(fromAPI);
                        if (animes.isEmpty()) {
                            break;
                        }
                        req.append("{id} in (");
                        animes.forEach(anime -> req.append(anime.getId())
                                .append(", "));
                        req.setLength(req.length() - 2);
                        req.append(") and ");
                        break;
                    case "minYear":
                        req.append("{season.year} >= ");
                        req.append(request.getMinYear());
                        req.append(" and ");
                        break;
                    case "maxYear":
                        req.append("{season.year} <= ");
                        req.append(request.getMaxYear());
                        req.append(" and ");
                        break;
                    case "minChapters":
                        req.append("{type.episodes} >= ");
                        req.append(request.getMinChapters());
                        req.append(" and ");
                        break;
                    case "maxChapters":
                        req.append("{type.episodes} <= ");
                        req.append(request.getMaxChapters());
                        req.append(" and ");
                        break;
                    case "status":
                        req.append("{status.string} == \"");
                        req.append(request.getStatus());
                        req.append("\" and ");
                        break;
                    case "genres":
                        if (request.getGenres().length == 0) {
                            break;
                        }
                        req.append("(");
                        Arrays.stream(request.getGenres()).forEach(
                                genre -> req.append("\"" + genre + "\", ")
                        );
                        req.setLength(req.length() - 2);
                        req.append(")");
                        req.append(" in {genres}");
                        req.append(" and ");
                        break;
                }
            }
        }
        if (!valuePresent) {
            return null;
        }
        req.setLength(req.length() -5);

        System.out.println(req);
        return String.format(GET_JSON_ANIME_INFO_URL_WITH_FILTERS, req);
    }

    private Object getFieldValue(Field field, Object target) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AnimeEntity getAnimeById(String animeId) {
        if (animeId == null) {
            return null;
        }
        String response = getResponseFromAPI(animeId, GET_JSON_ANIME_INFO_URL_BY_ID);
        return getAnimeEntitiesFromString(response).get(0);
    }

    private String getResponseFromAPI(String parameter, String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format(url, parameter))
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<AnimeEntity> getAnimeEntitiesFromString(String response) {
        JSONObject jsonpObject = new JSONObject(response);
        JSONArray list = jsonpObject.getJSONArray("list");
        List<AnimeEntity> animes = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            animes.add(jsonToAnimeEntity.convert((JSONObject) list.get(i)));
        }

        return animes;
    }
}
