package com.openclassrooms.chatop.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.repository.MessageRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Service
@RequiredArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;

	/*
	 * public Optional<Message> getMessage(final Long id) { return
	 * messageRepository.findById(id); }
	 * 
	 * public Iterable<Message> getMessages() { return messageRepository.findAll();
	 * }
	 * 
	 * public void deleteMessage(final Long id) { messageRepository.deleteById(id);
	 * }
	 */

	public Message saveMessage(Message message) {
		Message savedMessage = messageRepository.save(message);
		return savedMessage;
	}

}
