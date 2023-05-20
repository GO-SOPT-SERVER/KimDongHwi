package sopt.org.ThirdSeminar.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.ThirdSeminar.common.dto.ApiResponseDto;
import sopt.org.ThirdSeminar.config.jwt.JwtService;
import sopt.org.ThirdSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.ThirdSeminar.exception.SuccessStatus;
import sopt.org.ThirdSeminar.service.BoardService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final JwtService jwtService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto create(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody @Valid final BoardRequestDto request) {
        boardService.create(Long.parseLong(jwtService.getJwtContents(accessToken)), request);
        return ApiResponseDto.success(SuccessStatus.CREATE_BOARD_SUCCESS);
    }
}