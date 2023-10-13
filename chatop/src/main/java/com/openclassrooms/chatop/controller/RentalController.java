package com.openclassrooms.chatop.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RentalController {

	private final RentalService rentalService;

	@GetMapping("/api/rentals")
	public Iterable<Rental> getRentals() {
		return rentalService.getRentals();
	}

	@GetMapping("/api/rentals/{id}")
	public Rental getRental(@PathVariable("id") final Long id) {
		Optional<Rental> rental = rentalService.getRental(id);
		if (rental.isPresent()) {
			return rental.get();
		} else {
			return null;
		}
	}

	@PostMapping("/api/rentals")
	ResponseEntity<String> rental(@RequestBody Rental rental) {
		rentalService.saveRental(rental);
		return new ResponseEntity<String>("{\"message\":\"Rental Created !\"}", HttpStatus.OK);
	}

	@PutMapping("/api/rentals/{id}")
	public ResponseEntity<String> updateTutorial(@PathVariable("id") long id, @RequestBody Rental rental) {
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

			rentalService.saveRental(currentRental);

			return new ResponseEntity<String>("{\"message\":\"Rental Updated !\"}", HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
