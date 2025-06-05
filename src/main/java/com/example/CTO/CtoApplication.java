package com.example.CTO;

import com.example.CTO.model.User;
import com.example.CTO.model.enums.UserRole;
import com.example.CTO.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class CtoApplication implements CommandLineRunner {

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CtoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUsername("admin");
		user.setPassword(bCryptPasswordEncoder.encode("admin"));
		user.setRole(UserRole.ROLE_ADMIN);
		userRepository.save(user);
	}
}
