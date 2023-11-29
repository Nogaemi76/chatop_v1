package com.openclassrooms.chatop.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.AuthService;
import com.openclassrooms.chatop.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private Logger logger = LogManager.getLogger();
	private final AuthService authService;

	private final ModelMapper modelMapper;

	private final TokenService tokenService;

	@PostMapping("/api/auth/register")
	public ResponseEntity<String> register(@RequestBody UserDto userDto) throws ParseException {
		User user = convertToEntity(userDto);
		if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
			return new ResponseEntity<String>("{}", HttpStatus.BAD_REQUEST);
		}

		// logger.info("Test");
		user.setCreatedAt(LocalDateTime.now());
		authService.register(user);
		return new ResponseEntity<String>("{\"token\":\"jwt\"}", HttpStatus.OK);
	}

	@PostMapping("/api/auth/login")
	public ResponseEntity<String> login(@RequestBody UserDto userDto) throws ParseException {
		User currentUser = convertToEntity(userDto);

		Optional<User> savedUser = authService.login(currentUser.getEmail());
		logger.info("savedUser");
		logger.info(savedUser);

		if (savedUser != null && savedUser.get().getEmail().equals(currentUser.getEmail())
				&& savedUser.get().getPassword().equals(currentUser.getPassword())) {

			return new ResponseEntity<String>("{\"token\":\"jwt\"}", HttpStatus.OK);

		} else
			return new ResponseEntity<String>("{\"message\":\"error\"}", HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/api/auth/me")
	public ResponseEntity<String> authme(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		logger.info(authorization);
		if (authorization.equals("Bearer jwt")) {

			// TO DO IMPLEMENT DTO & BODY
			return ResponseEntity.status(HttpStatus.OK).body("Response ok");

		} else
			return new ResponseEntity<String>("{}", HttpStatus.UNAUTHORIZED);
	}

	/*
	 * private UserDto convertToDto(User user) { UserDto userDto =
	 * modelMapper.map(user, UserDto.class); return userDto; }
	 */

	private User convertToEntity(UserDto userDto) throws ParseException {
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

	@PostMapping("/token")
	public String token(Authentication authentication) {
		logger.info("Token requested for user: '{}'", authentication.getName());
		String token = tokenService.generateToken(authentication);
		logger.info("Token granted: {}", token);
		return token;
	}

}
