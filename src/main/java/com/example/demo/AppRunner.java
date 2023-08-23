package com.example.demo;

import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Dispositivo;
import com.example.demo.entities.StatoDispositivo;
import com.example.demo.entities.TipoDispositivo;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.service.UtentePayload;
import com.example.demo.service.UtenteService;
import com.example.demo.sevicedispositivo.DispositivoService;
import com.github.javafaker.Faker;

@Component
public class AppRunner implements CommandLineRunner {
	@Autowired
	UtenteService uService;
	
	@Autowired
	DispositivoService dService;

	@Override
	public void run(String... args) throws BadRequestException {
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 21; i++) {
			String username =faker.pokemon().name();
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String email = faker.internet().emailAddress();
			String password = faker.lorem().characters(4, 10);
			UtentePayload user = new UtentePayload(username, name, surname, email, password);
			//uService.create(user);
		}
		
		Dispositivo dispositivo = new Dispositivo(TipoDispositivo.Smartphone, StatoDispositivo.Disponibile);
		dService.create(dispositivo, UUID.fromString("7deb3ab3-6626-48ea-9456-17abe4cd24f3"));
	}
}
