package com.openclassrooms.chatop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RentalDto {

	private long id;

	private String name;

	private BigDecimal surface;

	private BigDecimal price;

	private String picture;

	private String description;

	private Integer owner_id;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}
