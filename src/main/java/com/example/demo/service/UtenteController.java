package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Utente;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	private final UtenteService uService;

	@Autowired
	public UtenteController(UtenteService uService) {
		this.uService = uService;
	}

	@GetMapping
	public Page<Utente> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return uService.findAll(page, size, sortBy);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUser(@RequestBody UtentePayload body) {
		Utente created = uService.create(body);

		return created;
	}

	@GetMapping("/{userId}")
	public Utente findById(@PathVariable UUID userId) {
		return uService.findById(userId);

	}
	
	@PutMapping("/{userId}")
	public Utente updateUser(@PathVariable UUID userId, @RequestBody UtentePayload body) {
		return uService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) {
		uService.findByIdAndDelete(userId);
	}
}
