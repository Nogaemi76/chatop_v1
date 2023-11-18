package com.openclassrooms.chatop.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.dto.RentalDto;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RentalController {

	private final RentalService rentalService;

	private final ModelMapper modelMapper;

	@GetMapping("/api/rentals")
	public List<RentalDto> getRentals() {

		List<Rental> rentals = rentalService.getRentals();

		return rentals.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/api/rentals/{id}")
	public RentalDto getRental(@PathVariable("id") final Long id) {

		Optional<Rental> rental = rentalService.getRental(id);

		if (rental.isPresent()) {
			RentalDto rentalDto = convertToDto(rental.get());

			return rentalDto;

		} else {
			return null;
		}
	}

	@PostMapping("/api/rentals")
	ResponseEntity<String> rental(@RequestBody RentalDto rentalDto) throws ParseException {
		Rental rental = convertToEntity(rentalDto);
		rental.setCreatedAt(LocalDateTime.now());
		rentalService.saveRental(rental);
		return new ResponseEntity<String>("{\"message\":\"Rental Created !\"}", HttpStatus.OK);
	}

	@PutMapping("/api/rentals/{id}")
	public ResponseEntity<String> updateRental(@PathVariable("id") long id, @RequestBody RentalDto rentalDto)
			throws ParseException {

		Rental rental = convertToEntity(rentalDto);

		Optional<Rental> rentalData = rentalService.getRental(id);

		if (rentalData.isPresent()) {
			Rental currentRental = rentalData.get();

			String name = rental.getName();
			if (name != null) {
				currentRental.setName(name);
			}
			BigDecimal surface = rental.getSurface();
			if (surface != null) {
				currentRental.setSurface(surface);
			}
			BigDecimal price = rental.getPrice();
			if (price != null) {
				currentRental.setPrice(price);
			}
			String description = rental.getDescription();
			if (description != null) {
				currentRental.setDescription(description);
			}
			// String picture = rental.getPicture();
			// if (picture != null) {
			// currentRental.setPicture(picture);
			// }

			currentRental.setUpdatedAt(LocalDateTime.now());

			rentalService.saveRental(currentRental);

			return new ResponseEntity<String>("{\"message\":\"Rental Updated !\"}", HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private RentalDto convertToDto(Rental rental) {
		RentalDto rentalDto = modelMapper.map(rental, RentalDto.class);
		return rentalDto;
	}

	private Rental convertToEntity(RentalDto rentalDto) throws ParseException {
		Rental rental = modelMapper.map(rentalDto, Rental.class);
		return rental;
	}

}
