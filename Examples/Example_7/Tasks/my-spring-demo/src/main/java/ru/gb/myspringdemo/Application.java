package ru.gb.myspringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.myspringdemo.model.User;
import ru.gb.myspringdemo.repository.UserRepository;

@SpringBootApplication
public class Application {

	static long id = 1L;

    public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);

		saveUser(userRepository, "admin");
		saveUser(userRepository, "reader");
		saveUser(userRepository, "auth");
		saveUser(userRepository, "guest");
	}

	private static void saveUser(UserRepository userRepository, String login) {
		User user = new User();
		user.setId(id++);
		user.setLogin(login);
		user.setPassword(login);
		user.setRole(login);
		userRepository.save(user);
	}

}
