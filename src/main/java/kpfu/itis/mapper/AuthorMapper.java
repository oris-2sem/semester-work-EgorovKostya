package kpfu.itis.mapper;

import kpfu.itis.dto.request.AuthorRequest;
import kpfu.itis.dto.response.AuthorResponse;
import kpfu.itis.model.AuthorEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponse toResponse(AuthorEntity authorEntity);

    AuthorEntity toEntity(AuthorRequest request);

    List<AuthorResponse> toListResponse(List<AuthorEntity> all);
}
