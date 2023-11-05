package com.codingdojo.rocio.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //APIs
@RequestMapping("/base")
public class ControladorBase {
	
	//Petición tipo GET
	//URLs deben ser únicas, nombre de método TAMBIËN debe ser único
	//@RequestMapping(value="/", method=RequestMethod.GET)
	@GetMapping("/")
	public String home() {
		return "¡Hola desde SpringBoot!";
	}
			
	@GetMapping("/despliega")
	public String despliegaUsuarios() {
		String usuarios[]= {"Elena de Troya", "Juana de Arco", "Pablo Picasso"};
		String respuesta = "";
		for(int i=0; i<usuarios.length; i++) {
			respuesta += "<h2>"+usuarios[i]+"</h2>";
		}
		return respuesta;
		//para que esto funcione, relaunch el Application.java
	}
	
	@GetMapping("/hello")
	public String helloNombre (@RequestParam(value="nombre")String name) {
		return "<h1>¡Hola "+name+"!</h1>";
	}
	
	@GetMapping("/hello2")
	public String helloNombreApellido(@RequestParam( value="nombre")String name, @RequestParam(value="apellido")String lastName) {
		return "<h1>¡Hola "+name+" "+lastName+"!</h1>";
	}
	
	@GetMapping("/hola/{nombre}")
	public String helloPath(@PathVariable("nombre")String name) {
		return "<h1>¡Hola "+name+"!</h1>";
	}
	
	@GetMapping("/repite/{palabra}/{numero}")
		public String repite(@PathVariable("palabra")String palabra, @PathVariable("numero") int numero){
			String respuesta = "";
			
			for(int i=0; i<numero; i++) {
				respuesta+=palabra+" ";
			}
			return respuesta;
		}
	
	
	}
