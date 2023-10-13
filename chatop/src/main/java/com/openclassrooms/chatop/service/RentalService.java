package com.openclassrooms.chatop.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Service
@RequiredArgsConstructor
public class RentalService {

	private final RentalRepository rentalRepository;

	public Optional<Rental> getRental(final Long id) {
		return rentalRepository.findById(id);
	}

	public Iterable<Rental> getRentals() {
		return rentalRepository.findAll();
	}

	public void deleteRental(final Long id) {
		rentalRepository.deleteById(id);
	}

	public Rental saveRental(Rental rental) {
		Rental savedRental = rentalRepository.save(rental);
		return savedRental;
	}

}
