package sopt.org.SecondAssginment.service;

import org.springframework.stereotype.Service;
import sopt.org.SecondAssginment.controller.post.dto.PostRequestDTO;
import sopt.org.SecondAssginment.domain.Post;
import sopt.org.SecondAssginment.domain.User;

import static sopt.org.SecondAssginment.SecondSeminarApplication.postList;

@Service
public class PostService {

    public Long register(PostRequestDTO request) {

        // 받아온 request 데이터를 토대로 실제 User 객체 생성
        Post newPost = new Post(
                request.getUser(),
                request.getTitle(),
                request.getText()
        );

        // 데이터베이스에 저장
        postList.add(newPost);
        newPost.setId((long) postList.size());

        return newPost.getId();
    }

    public Post getPost(Long id) {
        return postList.get(Math.toIntExact(id));
    }

    public String findPost(String title) {
        String text="해당 제목의 포스트가 없습니다.";
        for (Post post : postList) {
            if (post.getTitle().equals(title)) {
                return post.getText();
            }
        }
        return text;
    }
}
