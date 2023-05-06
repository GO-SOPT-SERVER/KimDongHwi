package sopt.org.ThirdSeminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ThirdSeminarApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirdSeminarApplication.class, args);
	}

}
