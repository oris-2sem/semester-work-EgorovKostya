package kpfu.itis.service.impl;

import javax.transaction.Transactional;

import kpfu.itis.dto.request.CommentRequest;
import kpfu.itis.dto.request.TitleRegistrationRequest;
import kpfu.itis.exception.database.TitleNotFoundException;
import kpfu.itis.model.AuthorEntity;
import kpfu.itis.model.CommentEntity;
import kpfu.itis.model.TitleEntity;
import kpfu.itis.model.enums.Type;
import kpfu.itis.repository.AuthorRepository;
import kpfu.itis.repository.CommentRepository;
import kpfu.itis.repository.TitleRepository;
import kpfu.itis.service.MinioService;
import kpfu.itis.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.sql.Date;
@Service
@RequiredArgsConstructor
@Transactional
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;

    private final AuthorRepository authorRepository;

    private final CommentRepository commentRepository;

    private final MinioService minioService;


    public List<Map.Entry<TitleEntity, String>> getTitles(PageRequest pageRequest) {
        List<TitleEntity> titles = titleRepository.findAll(pageRequest).getContent();
        return getTitlesWithImage(titles);
    }

    @Override
    public Map.Entry<TitleEntity, String> getTitleById(UUID titleId) {

        TitleEntity titleEntity = titleRepository.findById(titleId)
                .orElseThrow(() -> new TitleNotFoundException(String.format("Not found title with this %s", titleId)));
        return getTitleWithImage(titleEntity);
    }

    @Override
    public void deleteById(UUID titleId) {
        titleRepository.deleteById(titleId);
    }

    @Override
    public List<Map.Entry<TitleEntity, String>> findByName(String name, PageRequest pageRequest) {
        if (name == null) {
            return getTitles(pageRequest);
        }
        List<TitleEntity> byName = titleRepository.findByName(name, pageRequest).getContent();
        return getTitlesWithImage(byName);
    }

    @Override
    public void save(TitleRegistrationRequest request) {
        String fileName = minioService.saveImage(request);
        List<AuthorEntity> authors = authorRepository.findAllById(Arrays.stream(request.getAuthorIds()).toList());
        titleRepository.save(TitleEntity.builder()
                .alternativeName(request.getAlternativeName())
                .description(request.getDescription())
                .pages(request.getPages())
                .link(request.getLink())
                .name(request.getName())
                .status(request.getStatus())
                .url(fileName)
                .authors(new HashSet<>(authors))
                .fullDescription(request.getFullDescription())
                .released(Date.valueOf(request.getReleased()))
                .type(Type.valueOf(request.getType()))
                .build());

    }
    public Map.Entry<TitleEntity, String> getTitleWithImage(TitleEntity title) {
        byte[] image = minioService.getImage(title.getUrl());
        StringBuilder builder = new StringBuilder();
        String[] split = title.getUrl().split("\\.");
        builder.append("data:image/")
                .append(split[1])
                .append(";base64,")
                .append(Base64.getEncoder().encodeToString(image));
        return new AbstractMap.SimpleEntry<>(title, builder.toString());
    }

    public List<Map.Entry<TitleEntity, String>> getTitlesWithImage(Collection<TitleEntity> titles) {
        List<Map.Entry<TitleEntity, String>> titlesWithImages = new ArrayList<>();
        titles.stream().forEach(x -> {
            titlesWithImages.add(getTitleWithImage(x));
        });
        return titlesWithImages;
    }

    @Override
    public void addComment(UUID titleId, String value, String username) {
        TitleEntity titleEntity = titleRepository.findById(titleId)
                .orElseThrow(() -> new TitleNotFoundException(String.format("Not found title with this %s", titleId)));

        CommentEntity comment = commentRepository.save(CommentEntity.builder()
                .value(username + ": " + value)
                .build());
        titleEntity.getComments()
                .add(comment);
    }

    @Override
    public TitleEntity getPopular() {
        return titleRepository.findPopularTitle();
    }
}
