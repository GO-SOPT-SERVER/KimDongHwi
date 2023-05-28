package sopt.org.ThirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.org.ThirdSeminar.controller.dto.request.BoardImageListRequestDto;
import sopt.org.ThirdSeminar.domain.Board;
import sopt.org.ThirdSeminar.domain.Image;
import sopt.org.ThirdSeminar.domain.User;
import sopt.org.ThirdSeminar.exception.ErrorStatus;
import sopt.org.ThirdSeminar.exception.model.NotFoundException;
import sopt.org.ThirdSeminar.infrastructure.BoardRepository;
import sopt.org.ThirdSeminar.infrastructure.ImageRepository;
import sopt.org.ThirdSeminar.infrastructure.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public void create(Long userId, List<String> boardImageUrlList, BoardImageListRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_USER_EXCEPTION, ErrorStatus.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Board newBoard = Board.newInstance(
                user,
                request.getTitle(),
                request.getContent(),
                request.getIsPublic()
        );

        boardRepository.save(newBoard);

        for (String boardImageUrl : boardImageUrlList) {
            imageRepository.save(Image.newInstance(newBoard, boardImageUrl));
        }
    }
}
