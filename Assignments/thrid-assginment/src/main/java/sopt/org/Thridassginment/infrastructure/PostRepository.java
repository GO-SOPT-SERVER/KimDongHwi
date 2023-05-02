package sopt.org.Thridassginment.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.Thridassginment.domain.Post;

import java.util.List;

public interface PostRepository extends Repository<Post, Long> {
    void save(Post post);

    Post findById(Long id);

    List<Post> findAll();
}
