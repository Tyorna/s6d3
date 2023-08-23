package com.example.demo.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UtentePayload {
	    private String username;
		private String name;
		private String surname;
		private String email;
		private String password;
}
