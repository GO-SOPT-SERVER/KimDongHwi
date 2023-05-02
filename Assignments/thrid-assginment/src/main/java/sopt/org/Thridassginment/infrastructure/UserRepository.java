package sopt.org.Thridassginment.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.Thridassginment.domain.User;


public interface UserRepository extends Repository<User, Long> {
    void save(User user);

    User findById(Long id);

    User findByEmail(String email);

    User findByNickname(String nickname);
}