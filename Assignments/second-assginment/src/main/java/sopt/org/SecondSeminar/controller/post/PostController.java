package sopt.org.SecondSeminar.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.SecondSeminar.controller.post.dto.PostRequestDTO;
import sopt.org.SecondSeminar.domain.Post;
import sopt.org.SecondSeminar.service.PostService;

import static sopt.org.SecondSeminar.SecondSeminarApplication.postList;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts/create")
    public String register(@RequestBody final PostRequestDTO request) {
        Long postId = postService.register(request);
        System.out.println(postList.get(postId.intValue() - 1).toString());

        return postId + "번 게시물이 등록되었습니다!";
    }

    @GetMapping("/posts/{posdId}")
    public String getOne(@PathVariable final Long posdId) {
        try {
            Post post = postService.getPost(posdId);
            return "Title: " + post.getTitle() + "\n  Text : " +post.getText();
        } catch (IndexOutOfBoundsException e) {
            return "찾으시는 포스트가 존재하지 않습니다.";
        }

    }

    @GetMapping("/posts/search")
    public String search(@RequestParam final String title) {
        String text = postService.findPost(title);

        return "Title: " + title + "\n  Text : " +text;
    }

}
