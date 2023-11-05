package com.codingdojo.rocio.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.rocio.modelos.Usuario;
import com.codingdojo.rocio.servicios.Servicios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorUsuarios {
	
	@Autowired
	private Servicios servicio;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario")Usuario nuevaUsuario) {
		return "index.jsp";
	}
	
	
	@PostMapping("/registro")
	public String registro(@Valid @ModelAttribute("nuevoUsuario") Usuario nuevoUsuario, BindingResult result, HttpSession session) {
		
		servicio.registrar(nuevoUsuario, result);
		
		if(result.hasErrors()) {
			return "index.jsp";
		}else {
			//guardar en sesión el nuevo usuario
			session.setAttribute("usuarioEnSesion", nuevoUsuario);
			return "redirect:/tasks";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email,
						@RequestParam("password")String password,
						RedirectAttributes redirectAttributes,
						HttpSession session) {
		Usuario usuarioInicioSesion = servicio.login(email, password);
		
		if(usuarioInicioSesion == null) {
			//tiene algo incorrecto
			//flash permite enviar errores de validacion a un form normal
			redirectAttributes.addFlashAttribute("error_login", "El correo/password es incorrecto");
			return "redirect:/";
		}else {
			//guardamos en sesion al usuario que quiere iniciar sesion
			session.setAttribute("usuarioEnSesion", usuarioInicioSesion);
			return "redirect:/tasks";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usuarioEnSesion");
		
		return "redirect:/";
	}

}
