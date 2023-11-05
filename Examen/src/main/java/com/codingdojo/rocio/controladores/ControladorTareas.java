package com.codingdojo.rocio.controladores;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.rocio.modelos.Tarea;
import com.codingdojo.rocio.modelos.Usuario;
import com.codingdojo.rocio.servicios.Servicios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorTareas {
	
	
	@Autowired
	private Servicios servicios;
	
	
	@GetMapping("/tasks")
	public String dashboard(HttpSession session, Model model) {
		/*Revisamos que el usuario haya iniciado sesión*/
		Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioTemporal == null) {
			return "redirect:/";
		}
		List<Usuario> asignatarios = servicios.obtenerUsuarios();
	    model.addAttribute("asignatarios", asignatarios);
		model.addAttribute("tareas", servicios.todasTareas());
		model.addAttribute("usuarios", servicios.obtenerUsuarios());
		
		return "tasks.jsp";
	}
	
	@GetMapping("/new")
	public String nuevo(HttpSession session, @ModelAttribute("tarea")Tarea tarea, Model model) {
		/*---- REVISAMOS INICIO DE SESIÓN ----*/
		Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioTemporal == null) {
			return "redirect:/";
		}
		/*---- REVISAMOS INICIO DE SESIÓN ----*/
		
		List<Usuario> asignatarios = servicios.obtenerUsuarios();
	    model.addAttribute("asignatarios", asignatarios);
	    
	    List<String> prioridades = Arrays.asList("Baja", "Media", "Alta"); //esta opción para crear las prioridades me pareció fácil, también consideré hacerla como los estados en Eventos
	    model.addAttribute("prioridades", prioridades);
		
		Usuario miUsuario = servicios.encontrarUsuario(usuarioTemporal.getId());
		model.addAttribute("usuario", miUsuario);
		
		return "new.jsp";
		
	}
	
	@PostMapping("/crear")
	public String crear(HttpSession session, @Valid @ModelAttribute("tarea")Tarea tarea, BindingResult result) {
		
		/*---- REVISAMOS INICIO DE SESIÓN ----*/
		Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioTemporal == null) {
			return "redirect:/";
		}
		
		if(result.hasErrors()) {
			return "new.jsp";
		}else {
			servicios.guardarTarea(tarea);
			
			return "redirect:/tasks";
		}
		
	}
	
	
	@GetMapping("/delete/{id}")
	public String quitar(@PathVariable("id") Long tareaId,
						 HttpSession session) {
		/*====== REVISAMOS SESION ======*/
		Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioTemporal == null) {
			return "redirect:/";
		}
		/*====== REVISAMOS SESION ======*/
		
		servicios.quitarTarea(usuarioTemporal.getId(), tareaId);
		return "redirect:/tasks";
	}
	
	@DeleteMapping("/delete/{id}")
	public String borrar(@PathVariable("id")Long id) {
		servicios.quitarTarea(id, id);
		return "redirect:/tasks";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id")Long id, @ModelAttribute("tarea")Tarea tarea, HttpSession session, Model model) {
		
		/*---- REVISAMOS INICIO DE SESIÓN ----*/
		Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioTemporal == null) {
			return "redirect:/";
		}
		
		//objeto tarea
		Tarea tareaAEditar = servicios.encontrarTarea(id);
		
		//revisar que el usuario en sesión sea el creador
		if(usuarioTemporal.getId() != tareaAEditar.getCreador().getId()) {
			return "redirect:/tasks";
		}
		
		model.addAttribute("tarea", tareaAEditar);
	    
		List<Usuario> asignatarios = servicios.obtenerUsuarios();
	    model.addAttribute("asignatarios", asignatarios);
	    List<String> prioridades = Arrays.asList("Baja", "Media", "Alta"); //esta opción para crear las prioridades me pareció fácil, también consideré hacerla como los estados en Eventos
	    model.addAttribute("prioridades", prioridades);
		
		Usuario miUsuario = servicios.encontrarUsuario(usuarioTemporal.getId());
		model.addAttribute("usuario", miUsuario);
		
		
		return "edit.jsp";
	}
	
	@PutMapping("/actualizar")
	public String update(HttpSession session, @Valid @ModelAttribute("tarea")Tarea tarea, BindingResult result, Model model) {
	
		/*---- REVISAMOS INICIO DE SESIÓN ----*/
		Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioTemporal == null) {
			return "redirect:/";
		}
	
		if(result.hasErrors()) {
			
			
			List<Usuario> asignatarios = servicios.obtenerUsuarios();
		    model.addAttribute("asignatarios", asignatarios);
		    
		    List<String> prioridades = Arrays.asList("Baja", "Media", "Alta"); //esta opción para crear las prioridades me pareció fácil, también consideré hacerla como los estados en Eventos
		    model.addAttribute("prioridades", prioridades);
			
			Usuario miUsuario = servicios.encontrarUsuario(usuarioTemporal.getId());
			model.addAttribute("usuario", miUsuario);
			
			return "edit.jsp";
		}else {
			servicios.guardarTarea(tarea);
			return "redirect:/tasks";
		}
	
	}
	
	@GetMapping("/show/{id}")
	public String mostrar(@PathVariable("id")Long id, 
						Model model) {
		
		//obtener un objeto de usuario en base al ID
		Tarea tareaBuscada = servicios.encontrarTarea(id);
		model.addAttribute("tarea",tareaBuscada);
		
		return "show.jsp";
	}
	
	@GetMapping("/complete/{id}")
	public String completar(@PathVariable("id") Long tareaId,
			 HttpSession session) {
		/*====== REVISAMOS SESION ======*/
		Usuario usuarioTemporal = (Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioTemporal == null) {
		return "redirect:/";
		}
		/*====== REVISAMOS SESION ======*/
		
		servicios.quitarTarea(usuarioTemporal.getId(), tareaId);
		return "redirect:/tasks";
		}
	
	
}