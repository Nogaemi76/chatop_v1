package com.openclassrooms.chatop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

	private final MessageService messageService;

	@PostMapping("/api/messages")
	ResponseEntity<String> message(@RequestBody Message message) {

		if (message.getMessage() == null || message.getUser_id() == null || message.getRental_id() == null) {
			return new ResponseEntity<String>("{}", HttpStatus.BAD_REQUEST);
		}

		messageService.saveMessage(message);
		return new ResponseEntity<String>("{\"message\":\"Message sent with success\"}", HttpStatus.OK);
	}

}
