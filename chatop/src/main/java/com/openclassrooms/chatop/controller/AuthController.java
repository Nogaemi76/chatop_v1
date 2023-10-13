package com.openclassrooms.chatop.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private Logger logger = LogManager.getLogger();
	private final AuthService authService;

	@PostMapping("/api/auth/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
			return new ResponseEntity<String>("{}", HttpStatus.BAD_REQUEST);
		}

		logger.info("Test");
		authService.register(user);
		return new ResponseEntity<String>("{\"token\":\"jwt\"}", HttpStatus.OK);
	}

}
