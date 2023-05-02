package sopt.org.Thridassginment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.Thridassginment.controller.dto.request.UserRequestDto;
import sopt.org.Thridassginment.controller.dto.response.UserResponseDto;
import sopt.org.Thridassginment.domain.User;
import sopt.org.Thridassginment.exception.EmailException;
import sopt.org.Thridassginment.exception.NickNameException;
import sopt.org.Thridassginment.infrastructure.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto create(UserRequestDto request) throws EmailException, NickNameException {
        checkDuplicationEmail(request.getEmail());
        checkDuplicationNickname(request.getNickname());

        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();


        userRepository.save(user);

        return UserResponseDto.of(user.getId(), user.getNickname());
    }

    private void checkDuplicationEmail(String email) throws EmailException {
        User byEmail = userRepository.findByEmail(email);
        if (byEmail != null) {
            throw new EmailException();
        }
    }

    private void checkDuplicationNickname(String nickname) throws NickNameException {
        User byEmail = userRepository.findByNickname(nickname);
        if (byEmail != null) {
            throw new NickNameException();
        }
    }
}


