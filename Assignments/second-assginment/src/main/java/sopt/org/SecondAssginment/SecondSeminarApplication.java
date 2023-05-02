package sopt.org.SecondAssginment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sopt.org.SecondAssginment.domain.Account;
import sopt.org.SecondAssginment.domain.Post;
import sopt.org.SecondAssginment.domain.User;

import java.util.ArrayList;

@SpringBootApplication
public class SecondSeminarApplication {

	public static ArrayList<User> userList;
	public static ArrayList<Post> postList;
	public static ArrayList<Account> accountList;

	public static void main(String[] args) {
		SpringApplication.run(SecondSeminarApplication.class, args);
		userList = new ArrayList<>();
		postList = new ArrayList<>();
		accountList = new ArrayList<>();
	}

}
