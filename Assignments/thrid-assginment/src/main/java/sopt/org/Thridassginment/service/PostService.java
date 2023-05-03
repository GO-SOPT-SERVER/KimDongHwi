package sopt.org.Thridassginment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.Thridassginment.controller.dto.request.PostRequestDto;
import sopt.org.Thridassginment.controller.dto.response.OnePostResponseDto;
import sopt.org.Thridassginment.controller.dto.response.PostResponseDto;
import sopt.org.Thridassginment.domain.Post;
import sopt.org.Thridassginment.domain.User;
import sopt.org.Thridassginment.exception.NullPostException;
import sopt.org.Thridassginment.exception.NullUserException;
import sopt.org.Thridassginment.infrastructure.PostRepository;
import sopt.org.Thridassginment.infrastructure.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto post(PostRequestDto requestDto) throws NullUserException {

        //엔티티 조회
        try {
            User user = userRepository.findById(requestDto.getUserId());
            Post post = Post.createPost(user, requestDto.getTitle(), requestDto.getText());
            postRepository.save(post);
            return PostResponseDto.of(post.getId(), post.getUser().getNickname(), post.getTitle());
        } catch (NullPointerException e) {
            throw new NullUserException();
        }
    }

    public OnePostResponseDto findPostbyId(Long postId) throws NullPostException {
        try {
            Post post = postRepository.findById(postId);
            return OnePostResponseDto.of(post.getId(), post.getUser().getNickname(), post.getTitle(), post.getText(), post.getPostTime());
        } catch (NullPointerException e) {
            throw new NullPostException();
        }
    }

    public List<PostResponseDto> findPosts() {
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        postRepository.findAll().forEach( post -> {
            postResponseDtoList.add(PostResponseDto.of(post.getId(), post.getUser().getNickname(), post.getTitle()));
        });
        return postResponseDtoList;
    }
}
