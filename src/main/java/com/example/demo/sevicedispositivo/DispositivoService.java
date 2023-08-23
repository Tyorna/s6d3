package com.example.demo.sevicedispositivo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Dispositivo;
import com.example.demo.entities.Utente;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.UtenteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DispositivoService {
	private final DispositivoRepository dRepo;

	@Autowired
	public DispositivoService(DispositivoRepository dRepo) {
		this.dRepo = dRepo;
	}
	
	@Autowired
	private UtenteService uService;

    public Dispositivo create(Dispositivo dispositivo, UUID idUtente) {
        Utente utente = uService.findById(idUtente);
        dispositivo.setUtente(utente);
        return dRepo.save(dispositivo);
    }
    
    public Page<Dispositivo> findAll(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return dRepo.findAll(pageable);
	}
    
    public Dispositivo findById(UUID id) throws NotFoundException {
		return dRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Dispositivo findByIdAndUpdate(UUID id, DispositivoPayload body) throws NotFoundException {
		Dispositivo cercato = this.findById(id);
		cercato.setTipo(body.getTipo());
		cercato.setStato(body.getStato());
		cercato.setUtente(body.getUtente());
		return dRepo.save(cercato);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Dispositivo found = this.findById(id);
		log.info("utente Cancellato");
		dRepo.delete(found);
	}
}
