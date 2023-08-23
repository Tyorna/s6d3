package com.example.demo.exceptions;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorPayload {
	
		private String message;
		private Date timeStamp;
}
