package com.codingdojo.rocio.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codingdojo.rocio.modelos.Hobby;
import com.codingdojo.rocio.modelos.Usuario;
import com.codingdojo.rocio.servicios.Servicios;

@Controller
public class ControladorHobbies {
	
	@Autowired
	private Servicios servicio;
	
	@GetMapping("/asignar/{id}")
	public String asignar(@PathVariable("id") Long id,
						  Model model) {
		//Usuario al cual le voy a asignar los hobbies
		Usuario miUsuario = servicio.buscarUsuario(id);
		model.addAttribute("usuario", miUsuario);
		
		//Lista de Hobbies
		List<Hobby> hobbies = servicio.todosHobbies();
		model.addAttribute("hobbies", hobbies);
		
		return "/hobbies/asignar.jsp";
	}
	
	// /asignarHobby/4/2
	// usuario_id = 4; hobby_id = 2;
	@GetMapping("/asignarHobby/{usuario_id}/{hobby_id}")
	public String asignarHobby(@PathVariable("usuario_id") Long usuario_id,
							   @PathVariable("hobby_id") Long hobby_id)	 {
		
		//Llamar a un método en el servicio que haga esa asignación
		servicio.guardarUsuarioHobby(usuario_id, hobby_id); //guardarUsuarioHobby(4, 2);
		
		return "redirect:/dashboard";
		
	}
	
	@GetMapping("/quitarHobby/{usuario_id}/{hobby_id}")
	public String quitarHobby(@PathVariable("usuario_id") Long usuario_id,
						      @PathVariable("hobby_id") Long hobby_id) {
		
		//Llamar a un método en el servicio que quite ese hobby al usuario
		servicio.quitarUsuarioHobby(usuario_id, hobby_id);
		
		return "redirect:/dashboard";
	}
	
}