package com.openclassrooms.chatop.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;

	public User register(User user) {
		User registeredUser = userRepository.save(user);
		return registeredUser;
	}

}
