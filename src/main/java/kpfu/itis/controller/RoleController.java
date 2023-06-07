package kpfu.itis.controller;

import kpfu.itis.api.RoleApi;
import kpfu.itis.dto.request.RoleRequest;
import kpfu.itis.dto.response.RoleResponse;
import kpfu.itis.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoleController implements RoleApi {

    private final RoleService roleService;

    @Override
    public RoleResponse createRole(@RequestBody RoleRequest request) {
        return roleService.createRole(request);
    }

    @Override
    public void deleteRoleById(UUID roleId) {
        roleService.deleteById(roleId);
    }

    @Override
    public List<RoleResponse> getRoles() {
        return roleService.getRoles();
    }

    @Override
    public RoleResponse getRoleById(UUID roleId) {
        return roleService.getRoleById(roleId);
    }

    @Override
    public RoleResponse updateRoleById(UUID roleId, RoleRequest request) {
        return roleService.updateRoleById(roleId, request);
    }
}
