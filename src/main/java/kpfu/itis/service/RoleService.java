package kpfu.itis.service;

import kpfu.itis.dto.request.RoleRequest;
import kpfu.itis.dto.response.RoleResponse;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);

    void deleteById(UUID roleId);

    List<RoleResponse> getRoles();

    RoleResponse getRoleById(UUID roleId);

    RoleResponse updateRoleById(UUID uuid, RoleRequest request);
}
