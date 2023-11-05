package com.codingdojo.rocio.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.rocio.modelos.Etiqueta;
import com.codingdojo.rocio.modelos.Pregunta;
import com.codingdojo.rocio.modelos.Respuesta;
import com.codingdojo.rocio.servicios.Servicios;

import jakarta.validation.Valid;

@Controller
public class Controlador {
	
	@Autowired
	private Servicios servicio;
	
	@GetMapping("/")
	public String inicio() {
		return "redirect:/preguntas";
	}
	
	@GetMapping("/preguntas")
	public String preguntas(Model model) {
		List<Pregunta> preguntas = servicio.todasPreguntas();
		model.addAttribute("preguntas", preguntas);
		
		return "index.jsp";
	}
	
	@GetMapping("/nueva")
	public String nueva(@ModelAttribute("pregunta") Pregunta pregunta) {
		return "nueva.jsp";
	}
	
	@PostMapping("/crear")
	public String crear(@Valid @ModelAttribute("pregunta") Pregunta pregunta,
						BindingResult result,
						@RequestParam("textoEtiquetas") String textoEtiquetas) {
		
		if(result.hasErrors()) {
			return "nueva.jsp";
		} else {
			//textoEtiquetas = "java, programacion, tecnología"
			//listaEtiquetas = {"java", "programacion", "tecnología"}
			//trim() -> quitar los espacios alrededor
			//split() -> dividir el texto en base a un caracter
			String[] listaEtiquetas = textoEtiquetas.trim().split(",");
			List<Etiqueta> etiquetas = new ArrayList<>(); // Lista vacía de obj etiqueta
			
			/* listaEtiquetas = {"java", "programacion", "tecnología"}
			 * 
			 * tema = "java"
			 * eti = null (Porque NO existe esa etiqueta)
			 * nuevaEtiqueta = OBJ.tema = "java"
			 * etiquetas = {Obj(java)}
			 * 
			 * tema = "programacion"
			 * eti = Obj(programacion)
			 * etiquetas = {Obj(java), Obj(programacion)}
			 * 
			 * tema = "tecnología"
			 * eti = Obj(tecnologia)
			 * etiquetas = {Obj(java), Obj(programacion), Obj(tecnologia)}
			 */
			for(String tema:listaEtiquetas) {
				//Verificamos si el tema existe en las etiquetas de mi BD
				tema = tema.trim();
				
				Etiqueta eti = servicio.encuentraEtiqueta(tema);
				
				if(eti == null) {
					//No existe la etiqueta, la debemos de crear
					Etiqueta nuevaEtiqueta = new Etiqueta();
					nuevaEtiqueta.setTema(tema);
					servicio.guardarEtiqueta(nuevaEtiqueta);
					etiquetas.add(nuevaEtiqueta);
				} else {
					etiquetas.add(eti);
				}
			}
			
			pregunta.setEtiquetas(etiquetas);
			servicio.guardarPregunta(pregunta);
			return "redirect:/preguntas";
			
		}
		
	}
	
	@GetMapping("/preguntas/{id}")
	public String pregunta(@PathVariable("id")Long id, Model model,
							@ModelAttribute("respuesta")Respuesta respuesta) {
		
		Pregunta pregunta = servicio.encuentraPregunta(id);
		model.addAttribute("pregunta", pregunta);
		
		return "pregunta.jsp";
	}
	
	@PostMapping("/respuesta")
	public String respuesta(@Valid @ModelAttribute("respuesta")Respuesta respuesta, BindingResult result) {
		
		if(result.hasErrors()) {
			return "pregunta.jsp";
		}else {
			servicio.guardarRespuesta(respuesta);
			return "redirect:/preguntas/"+respuesta.getPregunta().getId();
		}
		
	}
	
}