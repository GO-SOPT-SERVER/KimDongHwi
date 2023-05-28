package sopt.org.ThirdSeminar.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sopt.org.ThirdSeminar.common.dto.ApiResponseDto;
import sopt.org.ThirdSeminar.config.jwt.JwtService;
import sopt.org.ThirdSeminar.config.resolver.UserId;
import sopt.org.ThirdSeminar.controller.dto.request.BoardImageListRequestDto;
import sopt.org.ThirdSeminar.exception.SuccessStatus;
import sopt.org.ThirdSeminar.external.client.aws.S3Service;
import sopt.org.ThirdSeminar.service.BoardService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@SecurityRequirement(name = "JWT Auth")
public class BoardController {

    private final BoardService boardService;
    private final JwtService jwtService;
    private final S3Service s3Service;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto create(
            @UserId Long userId,
            @ModelAttribute @Valid final BoardImageListRequestDto request) {
        List<String> boardThumbnailImageUrlList = s3Service.uploadImages(request.getBoardImages(), "board");
        boardService.create(userId, boardThumbnailImageUrlList, request);
        return ApiResponseDto.success(SuccessStatus.CREATE_BOARD_SUCCESS);
    }
}