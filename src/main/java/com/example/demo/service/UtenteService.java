package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Utente;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {

	private final UtenteRepository uRepo;

	@Autowired
	public UtenteService(UtenteRepository uRepo) {
		this.uRepo = uRepo;
	}

    public Utente create(UtentePayload body) {
	uRepo.findByUsername(body.getEmail()).ifPresent(user -> {
		throw new BadRequestException("L'utente è già stato utilizzato");
	});
	Utente newUser = new Utente(body.getUsername(),body.getName(), body.getSurname(), body.getEmail(), body.getPassword());
	return uRepo.save(newUser);
}
    
    public Page<Utente> findAll(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return uRepo.findAll(pageable);
	}
    
    public Utente findById(UUID id) throws NotFoundException {
		return uRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Utente findByIdAndUpdate(UUID id, UtentePayload body) throws NotFoundException {
		Utente cercato = this.findById(id);
		cercato.setUsername(body.getUsername());
		cercato.setName(body.getName());
		cercato.setSurname(body.getSurname());
		cercato.setEmail(body.getEmail());
		return uRepo.save(cercato);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		log.info("utente Cancellato");
		uRepo.delete(found);
	}
	
	public Utente findByEmail(String email) {
		return uRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
	
	public Utente findByUsername(String username) {
		return uRepo.findByEmail(username)
				.orElseThrow(() -> new NotFoundException("Username" + username + " non trovata"));
	}
}