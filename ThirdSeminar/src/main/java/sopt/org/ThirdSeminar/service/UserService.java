package sopt.org.ThirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.ThirdSeminar.controller.dto.request.UserLoginRequestDto;
import sopt.org.ThirdSeminar.controller.dto.request.UserRequestDto;
import sopt.org.ThirdSeminar.controller.dto.response.UserResponseDto;
import sopt.org.ThirdSeminar.domain.User;
import sopt.org.ThirdSeminar.exception.ErrorStatus;
import sopt.org.ThirdSeminar.exception.model.BadRequestException;
import sopt.org.ThirdSeminar.exception.model.ConflictException;
import sopt.org.ThirdSeminar.exception.model.NotFoundException;
import sopt.org.ThirdSeminar.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto create(UserRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException(ErrorStatus.ALREADY_EXIST_USER_EXCEPTION, ErrorStatus.ALREADY_EXIST_USER_EXCEPTION.getMessage());
        }
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        return UserResponseDto.of(user.getId(), user.getNickname());
    }

    @Transactional
    public Long login(UserLoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_USER_EXCEPTION, ErrorStatus.NOT_FOUND_USER_EXCEPTION.getMessage()));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException(ErrorStatus.INVALID_PASSWORD_EXCEPTION, ErrorStatus.INVALID_PASSWORD_EXCEPTION.getMessage());
        }

        return user.getId();
    }
}
