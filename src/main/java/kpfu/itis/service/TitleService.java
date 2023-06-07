package kpfu.itis.service;

import kpfu.itis.dto.request.CommentRequest;
import kpfu.itis.dto.request.TitleRegistrationRequest;
import kpfu.itis.model.TitleEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TitleService {


    Map.Entry<TitleEntity, String> getTitleById(UUID titleId);

    void deleteById(UUID titleId);

    List<Map.Entry<TitleEntity, String>>  findByName(String name, PageRequest pageRequest);

    void save(TitleRegistrationRequest request);

    Map.Entry<TitleEntity, String> getTitleWithImage(TitleEntity titles);
    List<Map.Entry<TitleEntity, String>> getTitlesWithImage(Collection<TitleEntity> titles);

    void addComment(UUID titleId, String value, String username);

    TitleEntity getPopular();
}
