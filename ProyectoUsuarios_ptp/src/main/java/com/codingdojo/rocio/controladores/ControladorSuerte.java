package com.codingdojo.rocio.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorSuerte {
	
	@GetMapping ("/suerte")
	public String suerte () {
		return "suerte.jsp";
	}
	
	@PostMapping("/damemisuerte")
	public String damemisuerte(@RequestParam(value="ciudad") String ciudadEnMetodo,
							@RequestParam(value="cualquierNombre") String nombreEnMetodo,
							@RequestParam(value="hobby") String hobbyEnMetodo,
							@RequestParam(value="animal") String animalEnMetodo,
							@RequestParam(value="numero") int numeroEnMetodo,
							
							RedirectAttributes flash,
							HttpSession session){
		
		System.out.println("En "+numeroEnMetodo+" años vas a vivir en "+ciudadEnMetodo+" con "+nombreEnMetodo+" y te vas a dedicar a "+hobbyEnMetodo+"."+" La próxima vez que veas un "+animalEnMetodo+" tendrás suerte.");
				
				return "redirect:/resultadoSuerte";
	

}
	@GetMapping ("/resultadoSuerte")
	public String resultado () {
		return "resultadoSuerte.jsp";
	}
}
