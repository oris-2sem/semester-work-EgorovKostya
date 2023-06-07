package kpfu.itis.service.impl;

import kpfu.itis.dto.request.PasswordUpdateRequest;
import kpfu.itis.dto.request.UserRegisterRequest;
import kpfu.itis.dto.request.UserUpdateRequest;
import kpfu.itis.exception.authentication.ForbiddenException;
import kpfu.itis.exception.database.TitleNotFoundException;
import kpfu.itis.exception.database.UserNotFoundException;
import kpfu.itis.exception.database.UsernameAlreadyExistsException;
import kpfu.itis.exception.validation.DifferentPasswordException;
import kpfu.itis.exception.validation.IncorrectPasswordException;
import kpfu.itis.mapper.UserMapper;
import kpfu.itis.model.RoleEntity;
import kpfu.itis.model.TitleEntity;
import kpfu.itis.model.UserEntity;
import kpfu.itis.repository.RoleRepository;
import kpfu.itis.repository.TitleRepository;
import kpfu.itis.repository.UserRepository;
import kpfu.itis.service.TitleService;
import kpfu.itis.service.UserService;
import kpfu.itis.util.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final TitleRepository  titleRepository;

    private final TitleService titleService;

    private final PasswordEncoder encoder;

    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователя с username %s не найдено", username)));
    }

    @Override
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        if (!userRegisterRequest.getPassword().equals(userRegisterRequest.getPasswordRepeat())) {
            throw new DifferentPasswordException(userRegisterRequest.getUsername());
        }
        if (userRepository.existsByUsername(userRegisterRequest.getUsername())) {
            throw new UsernameAlreadyExistsException(String.format("Никнейм %s уже занят", userRegisterRequest.getUsername()));
        }

        UserEntity user = userMapper.toUserEntity(userRegisterRequest);

        user.setRoles(new HashSet<>());
        user.setIsEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        Optional<RoleEntity> roleUser = roleRepository.findByName("ROLE_USER");
        user.getRoles().add(roleUser.get());
        userRepository.save(userRepository.save(user));
    }

    @Override
    public void addTitle(UUID userId, UUID titleId) {
        UserEntity userEntity = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(String.format("Пользователя с id %s не найдено", userId)));
        TitleEntity titleEntity = titleRepository.findById(titleId)
                .orElseThrow(() -> new TitleNotFoundException(String.format("Not found title with this %s", titleId)));
        userEntity.getFavoritesTitles().add(titleEntity);
    }

    @Override
    public UserEntity getById(UUID id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteTitle(UUID userId, UUID titleId) {
        if (!userId.equals(ContextHolder.getUserFromSecurityContext().getId())) {
            throw new ForbiddenException("Нет доступа");
        }
        UserEntity userEntity = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(String.format("Пользователя с id %s не найдено", userId)));
        TitleEntity titleEntity = titleRepository.findById(titleId)
                .orElseThrow(() -> new TitleNotFoundException(String.format("Not found title with this %s", titleId)));
        userEntity.getFavoritesTitles().remove(titleEntity);
    }

    @Override
    public UserEntity getUserById(UUID userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(String.format("Пользователя с id %s не найдено", userId)));
    }

    @Override
    public void updateUserById(UUID userId, UserUpdateRequest request) {
        UserEntity userEntity = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(String.format("Пользователя с id %s не найдено", userId)));;

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException(String.format("Никнейм %s уже занят", request.getUsername()));
        }
        userEntity.setUsername(request.getUsername());
        ContextHolder.getUserFromSecurityContext().setUsername(request.getUsername());
    }

    @Override
    public void updatePasswordById(UUID userId, PasswordUpdateRequest request) {
        UserEntity userEntity = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(String.format("Пользователя с id %s не найдено", userId)));
        if (!encoder.matches(request.getOldPassword(), userEntity.getPassword())) {
            throw new IncorrectPasswordException("Неверный пароль");
        }
        userEntity.setPassword(encoder.encode(request.getPassword()));
    }

    @Override
    public void deleteUserById(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(String.format("Пользователя с id %s не найдено", userId)));
        userEntity.setIsEnabled(false);
        SecurityContextHolder.clearContext();
    }

    @Override
    public List<Map.Entry<TitleEntity, String>> getTitlesByUserId(UUID uuid) {
        return titleService.getTitlesWithImage(getUserById(uuid).getFavoritesTitles());
    }
}
