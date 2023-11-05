package com.codingdojo.rocio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController//me permite mostrar URLs
public class ProyectoUsuariosPtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoUsuariosPtpApplication.class, args);
	}

}
