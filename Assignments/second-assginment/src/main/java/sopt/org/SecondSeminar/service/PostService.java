package sopt.org.SecondSeminar.service;

import org.springframework.stereotype.Service;
import sopt.org.SecondSeminar.controller.post.dto.PostRequestDTO;
import sopt.org.SecondSeminar.controller.user.dto.request.RegisterRequestDTO;
import sopt.org.SecondSeminar.domain.Post;
import sopt.org.SecondSeminar.domain.User;

import static sopt.org.SecondSeminar.SecondSeminarApplication.postList;
import static sopt.org.SecondSeminar.SecondSeminarApplication.userList;

@Service
public class PostService {

    public Long register(PostRequestDTO request) {

        // 받아온 request 데이터를 토대로 실제 User 객체 생성
        Post newPost = new Post(
                new User(request.getGender(), request.getName(), request.getContact(), request.getAge()),
                request.getTitle(),
                request.getText()
        );

        // 데이터베이스에 저장
        postList.add(newPost);
        newPost.setId((long) postList.size());

        // 저장한 유저 아이디 값 반환
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
