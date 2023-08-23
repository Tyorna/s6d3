package com.example.demo.sevicedispositivo;

import com.example.demo.entities.StatoDispositivo;
import com.example.demo.entities.TipoDispositivo;
import com.example.demo.entities.Utente;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class DispositivoPayload {

	private TipoDispositivo tipo;
	private StatoDispositivo stato;
	private Utente utente;
	
	public DispositivoPayload(TipoDispositivo tipo, StatoDispositivo stato) {
		this.tipo = tipo;
		this.stato = stato;
}
}
