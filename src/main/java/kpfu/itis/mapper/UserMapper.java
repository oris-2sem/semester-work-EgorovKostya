package kpfu.itis.mapper;

import kpfu.itis.dto.request.UserRegisterRequest;
import kpfu.itis.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(UserRegisterRequest request);
}
