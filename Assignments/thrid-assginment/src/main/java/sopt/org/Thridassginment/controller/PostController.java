package sopt.org.Thridassginment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.Thridassginment.common.dto.ApiResponseDto;
import sopt.org.Thridassginment.controller.dto.request.PostRequestDto;
import sopt.org.Thridassginment.controller.dto.response.OnePostResponseDto;
import sopt.org.Thridassginment.controller.dto.response.PostResponseDto;
import sopt.org.Thridassginment.exception.SuccessStatus;
import sopt.org.Thridassginment.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<PostResponseDto> create(@RequestBody @Valid final PostRequestDto postRequestDto) {
        return ApiResponseDto.success(SuccessStatus.POSTING_SUCCESS, postService.post(postRequestDto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List> getPosts() {
        return ApiResponseDto.success(SuccessStatus.LOADINGPOSTS_SUCCESS, postService.findPosts());
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<OnePostResponseDto> getPostbyId(@PathVariable final Long postId) {
        return ApiResponseDto.success(SuccessStatus.LOADINGPOST_SUCCESS, postService.findPostbyId(postId));
    }
}
