package sopt.org.Thridassginment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String text;

    private LocalDateTime postTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    private Post(User user, String title, String text) {
        setUser(user);
        this.title = title;
        this.text = text;
        this.postTime = LocalDateTime.now();
    }

    public void setUser(User user) {
        this.user = user;
        user.getPostList().add(this);
    }

    public static Post createPost(User user, String title, String text){
        return new Post(user, title, text);
    }

}
