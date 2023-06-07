package kpfu.itis.service.impl;

import kpfu.itis.dto.request.RoleRequest;
import kpfu.itis.dto.response.RoleResponse;
import kpfu.itis.exception.database.RoleAlreadyExistsException;
import kpfu.itis.exception.database.RoleNotFoundException;
import kpfu.itis.mapper.RoleMapper;
import kpfu.itis.model.RoleEntity;
import kpfu.itis.repository.RoleRepository;
import kpfu.itis.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;
    @Override
    public RoleResponse createRole(RoleRequest request) {
        if (roleRepository.existsByName(request.getName())) {
            throw new RoleAlreadyExistsException(
                    String.format("Роль с таким названием уже существует: %s", request.getName()));
        }
        request.setName("ROLE_" + request.getName().toUpperCase());
        RoleEntity role = roleRepository.save(roleMapper.toEntity(request));
        return roleMapper.toResponse(role);
    }

    @Override
    public void deleteById(UUID roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public List<RoleResponse> getRoles() {
        List<RoleEntity> roles = roleRepository.findAll();
        return roleMapper.toListResponse(roles);
    }

    @Override
    public RoleResponse getRoleById(UUID roleId) {
        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(String.format("Роль с таким id не найдена: ", roleId)));
        return roleMapper.toResponse(roleEntity);
    }

    @Override
    public RoleResponse updateRoleById(UUID roleId, RoleRequest request) {

        RoleEntity roleEntity = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(String.format("Роль с таким id не найдена: ", roleId)));
        roleEntity.setName(request.getName());
        return roleMapper.toResponse(roleEntity);
    }
}
