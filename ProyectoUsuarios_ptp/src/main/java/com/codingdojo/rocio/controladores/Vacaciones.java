package com.codingdojo.rocio.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Vacaciones {
	
	@GetMapping("/vacaciones/{destino}/{dia}/{nombre}")
	public String vacacionesView(@PathVariable(value="destino")String dest, @PathVariable(value="dia")int day, @PathVariable(value="nombre")String name) {
		String res ="<h1>"+name+" viajará por el mundo y conocerá "+dest+" el "+day+" de este mes.</h1>";
		
		return res;
	}

}
