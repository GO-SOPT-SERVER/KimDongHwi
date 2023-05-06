package sopt.org.ThirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.ThirdSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.ThirdSeminar.domain.Board;
import sopt.org.ThirdSeminar.domain.User;
import sopt.org.ThirdSeminar.exception.ErrorStatus;
import sopt.org.ThirdSeminar.exception.model.NotFoundException;
import sopt.org.ThirdSeminar.infrastructure.BoardRepository;
import sopt.org.ThirdSeminar.infrastructure.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void create(BoardRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_USER_EXCEPTION, ErrorStatus.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Board newBoard = Board.newInstance(
                user,
                request.getTitle(),
                request.getContent(),
                request.getIsPublic()
        );

        boardRepository.save(newBoard);
    }
}
