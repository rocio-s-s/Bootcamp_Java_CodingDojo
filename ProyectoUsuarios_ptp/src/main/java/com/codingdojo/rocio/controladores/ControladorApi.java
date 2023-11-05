package com.codingdojo.rocio.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.rocio.modelos.Usuario;
import com.codingdojo.rocio.servicios.Servicios;

@RestController
public class ControladorApi {
	
	@Autowired
	private Servicios servicio;
	
	@GetMapping("/api/usuarios")
	public List<Usuario> apiMuestraUsuarios(){
		return servicio.todosUsuarios();
	}
	
	@PostMapping("/api/usuarios")
	public Usuario apiCrearUsuario(@RequestParam("nombre")String nombre,
									@RequestParam("apellido")String apellido,
									@RequestParam("email")String email,
									@RequestParam("password")String password) {
		
		Usuario nuevoUsuario = new Usuario(nombre, apellido, email, password);
		return servicio.guardarUsuario(nuevoUsuario);
		
	}
	
	@DeleteMapping("/api/usuarios/{id}")
	public void apiBorrar(@PathVariable("id")Long id) {
		servicio.borrarUsuario(id);
	}
	
	@GetMapping("/api/usuarios/{id}")///api/usuarios/3
	public Usuario apiMostrar(@PathVariable("id")Long id) {
		return servicio.buscarUsuario(id);
	}
	
	@PutMapping("/api/usuarios/{id}")
	public Usuario apiEditar(@PathVariable("id")Long id,
			@RequestParam("nombre")String nombre,
			@RequestParam("apellido")String apellido,
			@RequestParam("email")String email,
			@RequestParam("password")String password) {
		
		Usuario usuarioActualizado = new Usuario(id, nombre, apellido, email, password);
		return servicio.guardarUsuario(usuarioActualizado);
	}
	
	@GetMapping("/api/usuariosjson")
	public ResponseEntity<List<Usuario>> apiMuestraUsuariosJson(){
		List<Usuario> usuarios = servicio.todosUsuarios();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}
	
	@PostMapping("/api/usuario/nuevo")
	public ResponseEntity<Usuario> apiNuevoUsuario(@RequestBody Usuario nuevoUsuario){
		servicio.guardarUsuario(nuevoUsuario);
		return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
	}
}
