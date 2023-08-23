package com.example.demo.sevicedispositivo;

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

import com.example.demo.entities.Dispositivo;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
	
	@Autowired
	private  DispositivoService dService;

	

	@GetMapping
	public Page<Dispositivo> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return dService.findAll(page, size, sortBy);
	}
	
	  @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	    public Dispositivo saveDispositivo(@RequestBody Dispositivo body) {
	        UUID idUtente = body.getUtente().getId();
	        Dispositivo dispositiviCreato = dService.create(body, idUtente);
	        return dispositiviCreato;
	    } 

	@GetMapping("/{dispositivoId}")
	public Dispositivo findById(@PathVariable UUID dispositivoId) {
		return dService.findById(dispositivoId);

	}
	
	@PutMapping("/{dispositivoId}")
	public Dispositivo updateUser(@PathVariable UUID dispositivoId, @RequestBody DispositivoPayload body) {
		return dService.findByIdAndUpdate(dispositivoId, body);
	}

	@DeleteMapping("/{dispositivoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID dispositivoId) {
		dService.findByIdAndDelete(dispositivoId);
	}
}
