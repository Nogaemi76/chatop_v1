package com.openclassrooms.chatop.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.UserDto;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final ModelMapper modelMapper;

	@GetMapping("/api/user/{id}")
	public UserDto getUser(@PathVariable("id") final Long id) {
		Optional<User> user = userService.getUser(id);
		if (user.isPresent()) {
			UserDto userDto = convertToDto(user.get());
			return userDto;
		} else {
			return null;
		}
	}

	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
