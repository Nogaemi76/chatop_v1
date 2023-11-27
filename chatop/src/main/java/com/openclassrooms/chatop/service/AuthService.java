package com.openclassrooms.chatop.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Service
@RequiredArgsConstructor
public class AuthService {

	private Logger logger = LogManager.getLogger();
	private final UserRepository userRepository;

	public User register(User user) {
		User registeredUser = userRepository.save(user);
		return registeredUser;
	}

	public Optional<User> login(String email) {
		Optional<User> savedUser = userRepository.findByEmail(email);

		logger.info("loggedUser in service");
		logger.info(savedUser);

		if (savedUser.isPresent()) {
			return savedUser;
		} else
			return null;

	}

}
