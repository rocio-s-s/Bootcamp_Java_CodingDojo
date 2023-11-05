package com.codingdojo.rocio.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.codingdojo.rocio.modelos.Usuario;
import com.codingdojo.rocio.servicios.Servicios;

import jakarta.servlet.http.HttpSession;

@Controller
public class ControladorEventos {
	
	@Autowired
	private Servicios servicio;
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session) {
		//queremos que entre a dashboard solo si ha iniciado sesion
		Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioTemporal == null) {
			return "redirect:/";
		}
		
		return "dashboard.jsp";
	}

}
