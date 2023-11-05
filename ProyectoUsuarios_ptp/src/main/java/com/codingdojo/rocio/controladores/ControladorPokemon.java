package com.codingdojo.rocio.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class ControladorPokemon {
	
	@GetMapping("/pokemones")
	public String pokemones(Model model) {
		String uri = "https://pokeapi.co/api/v2/pokemon/?limit=60";
		RestTemplate restTemplate = new RestTemplate();
		Object respuesta = restTemplate.getForObject(uri, Object.class);
		
		model.addAttribute("respuesta", respuesta);
		return "pokemones.jsp";
	}
	
	@GetMapping("/pokemon/{nombre}")
	public String pokemon(Model model, @PathVariable("nombre")String nombrePokemon) {
		String uri = "https://pokeapi.co/api/v2/pokemon/"+nombrePokemon;
		
		RestTemplate restTemplate = new RestTemplate();
		Object respuesta = restTemplate.getForObject(uri, Object.class);
		
		model.addAttribute("respuesta", respuesta);
		return "pokemon.jsp";
	}

}
