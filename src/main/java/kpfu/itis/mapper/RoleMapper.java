package kpfu.itis.mapper;

import kpfu.itis.dto.request.RoleRequest;
import kpfu.itis.dto.response.RoleResponse;
import kpfu.itis.model.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toResponse(RoleEntity role);

    RoleEntity toEntity(RoleRequest request);

    List<RoleResponse> toListResponse(List<RoleEntity> roles);
}
