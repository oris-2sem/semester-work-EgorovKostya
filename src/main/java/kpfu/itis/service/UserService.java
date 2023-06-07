package kpfu.itis.service;

import kpfu.itis.dto.request.PasswordUpdateRequest;
import kpfu.itis.dto.request.UserRegisterRequest;
import kpfu.itis.dto.request.UserUpdateRequest;
import kpfu.itis.model.TitleEntity;
import kpfu.itis.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public interface UserService extends UserDetailsService {
    
    void registerUser(UserRegisterRequest user);

    void addTitle(UUID userId, UUID titleId);

    UserEntity getById(UUID id);

    void deleteTitle(UUID userId, UUID titleId);

    UserEntity getUserById(UUID userId);

    void updateUserById(UUID userId, UserUpdateRequest request);

    void updatePasswordById(UUID userId, PasswordUpdateRequest request);

    void deleteUserById(UUID userId);

    List<Map.Entry<TitleEntity, String>> getTitlesByUserId(UUID uuid);
}
