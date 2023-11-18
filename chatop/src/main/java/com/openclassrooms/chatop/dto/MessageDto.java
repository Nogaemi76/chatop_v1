package com.openclassrooms.chatop.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto {

	private long id;

	private Integer rental_id;

	private Integer user_id;

	private String message;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}
