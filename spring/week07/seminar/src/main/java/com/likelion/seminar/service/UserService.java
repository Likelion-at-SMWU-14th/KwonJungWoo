package com.likelion.seminar.service;

import com.likelion.seminar.dto.UserResponse;
import com.likelion.seminar.dto.UserSaveRequest;
import com.likelion.seminar.dto.UserUpdateRequest;
import com.likelion.seminar.entity.User;
import com.likelion.seminar.global.exception.DuplicateEmailException;
import com.likelion.seminar.global.exception.UserNotFoundException;
import com.likelion.seminar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(UserSaveRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }
        userRepository.save(
                User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .age(request.getAge())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        user.update(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getAge()
        );

        return UserResponse.from(user);
    }

}
