package com.openclassrooms.chatop.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class resourceController {

	@GetMapping("/")
	public String getResource(Principal principal) {
		return "Hello " + principal.getName();
	}

}
