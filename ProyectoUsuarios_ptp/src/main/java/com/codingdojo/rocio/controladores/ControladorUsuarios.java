package com.codingdojo.rocio.controladores;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.rocio.modelos.Salon;
import com.codingdojo.rocio.modelos.Usuario;
import com.codingdojo.rocio.servicios.Servicios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorUsuarios {
	
	@Autowired
	public Servicios servicio;
	
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		//String titulo="Título para mis usuarios"
		model.addAttribute("titulo","Usuarios");
		
		String usuarios[]={"Elena de Troya", "Pablo Picatso", "Juana de Arco"};
		model.addAttribute("listaUsuarios", usuarios);
		
		HashMap<String, String> paises = new HashMap<String, String>();
		
		paises.put("México", "CDMX");
		paises.put("Estados Unidos", "Washington DC");
		paises.put("El Salvador", "San Salvador");
		
		model.addAttribute("paises", paises);
		
		return "usuarios.jsp";
	}
	
	/*Para formularios necesitamos 2 rutas, una que muestre el formulario y otras que reciba el formulario*/
	
	@GetMapping("/formulario")
	public String formulario() {
		return "formulario.jsp";
	}
	
	@PostMapping("/registrarme")
	public String registrarme(@RequestParam(value="nombre") String nombreEnMetodo,
							@RequestParam(value="email") String emailEnMetodo,
							RedirectAttributes flash,/*permite enviar mensajes de validación*/
							HttpSession session/*Permite guardar info en sesion*/){
		
		System.out.println("El nombre fue: "+nombreEnMetodo);
		System.out.println("El email fue: "+emailEnMetodo);
		
		//validación
		if(nombreEnMetodo.equals("")) {
			flash.addFlashAttribute("errorNombre", "Por favor proporciona tu nombre");
			return "redirect:/formulario";
		}
		
		//guardamos sesión
		session.setAttribute("nombreUsuario", nombreEnMetodo);//(variable, valor)
		
		return "redirect:/bienvenida";
	}
	
	@GetMapping("/bienvenida")
	public String bienvenida(HttpSession session) {
		
		//creamos un objeto de sesión
		Object nombreEnSesion = session.getAttribute("nombreUsuario");
		if(nombreEnSesion !=null) {
			String nombre = (String)nombreEnSesion;
			System.out.println("El nombre guardado es: "+nombre);
		}else {
			System.out.println("No se ha registrado");
		}
		
		return "bienvenida.jsp";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		
		List<Usuario> usuarios = servicio.todosUsuarios();
		model.addAttribute("usuarios", usuarios);
		
		return "dashboard.jsp";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(@ModelAttribute("usuario")Usuario usuario, Model model) {
		List<Salon> salones = servicio.todosSalones();
		model.addAttribute("salones", salones);
		
		return "nuevo.jsp";
	}
	
	@PostMapping("/crear")//@Valid me permite validar la info del objeto
	public String crear(@Valid @ModelAttribute("usuario")Usuario usuario, BindingResult result, Model model) {
	/*permite enviar los mensajes de validación*/
	
		if(result.hasErrors()){
			List<Salon> salones = servicio.todosSalones();
			model.addAttribute("salones", salones);
			return "nuevo.jsp";
		}else{
			servicio.guardarUsuario(usuario);
			return "redirect:/dashboard";
		}
		
	}
	
	//mostrar/5
	@GetMapping("/mostrar/{id}")
	public String mostrar(@PathVariable("id")Long id, 
						Model model) {
		
		//obtener un objeto de usuario en base al ID
		Usuario usuarioBuscado = servicio.buscarUsuario(id);
		model.addAttribute("usuario",usuarioBuscado);
		
		return "mostrar.jsp";
	}
	
	//borrar/5
	@DeleteMapping("/borrar/{id}")
	public String borrar(@PathVariable("id")Long id) {
		servicio.borrarUsuario(id);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")Long id,
							@ModelAttribute("usuario")Usuario usuario,
							Model model) {
		
		//Objeto de usuario que estoy editando
		Usuario usuarioBuscado = servicio.buscarUsuario(id);
		model.addAttribute("usuario",usuarioBuscado);
		List<Salon> salones = servicio.todosSalones();
		model.addAttribute("salones", salones);
		
		return "editar.jsp";
	}
	
	@PutMapping("/actualizar/{id}")
	public String actualizar(@Valid @ModelAttribute("usuario")Usuario usuario, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			List<Salon> salones = servicio.todosSalones();
			model.addAttribute("salones", salones);
			return "editar.jsp";
		}else {
			//guardar otra vez el usuario
			servicio.guardarUsuario(usuario);
			return "redirect:/dashboard";
		}
		
	}
	//buscar/na
	//String palabra ="na"
	@GetMapping("/buscar/{palabra}")
	public String buscar(@PathVariable("palabra")String palabra,
						Model model) {
		List<Usuario>usuariosConPalabra = servicio.buscarPorNombre(palabra);
		model.addAttribute("usuarios",usuariosConPalabra);
		
		model.addAttribute("palabra", palabra);
		
		return "buscar.jsp";
	}
	
	@PostMapping("/busqueda")
	public String busqueda(@RequestParam(value="palabra")String palabra) {
		return "redirect:/buscar/"+palabra;
	}
	
	@GetMapping("/top10")
	public String top(Model model) {
		List<Usuario> usuariosTop = servicio.top10();
		model.addAttribute("usuarios",usuariosTop);
		return "top10.jsp";
	}
	
}
