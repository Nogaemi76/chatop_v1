package com.openclassrooms.chatop.controller;

import java.text.ParseException;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.MessageDto;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.service.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {

	private final MessageService messageService;

	private final ModelMapper modelMapper;

	@PostMapping("/api/messages")
	ResponseEntity<String> message(@RequestBody MessageDto messageDto) throws ParseException {

		if (messageDto.getMessage() == null || messageDto.getUser_id() == null || messageDto.getRental_id() == null) {
			return new ResponseEntity<String>("{}", HttpStatus.BAD_REQUEST);
		}
		Message message = convertToEntity(messageDto);

		message.setCreatedAt(LocalDateTime.now());
		messageService.saveMessage(message);

		return new ResponseEntity<String>("{\"message\":\"Message sent with success\"}", HttpStatus.OK);
	}

	private Message convertToEntity(MessageDto messageDto) throws ParseException {
		Message message = modelMapper.map(messageDto, Message.class);
		return message;
	}

}
