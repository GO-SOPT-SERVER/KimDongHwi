package sopt.org.ThirdSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.ThirdSeminar.domain.Image;

public interface ImageRepository extends Repository<Image, Long> {
    void save(Image image);
}
