package com.codingdojo.rocio.servicios;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.rocio.modelos.Usuario;
import com.codingdojo.rocio.repositorios.RepositorioUsuarios;

@Service
public class Servicios {
	
	@Autowired
	private RepositorioUsuarios ru;
	
	
	/* Método que me regitre un nuevo usuario */
	public Usuario registrar(Usuario nuevoUsuario, BindingResult result) {
		//Comparamos contraseñas
		String contrasena = nuevoUsuario.getPassword();
		String confirmacion = nuevoUsuario.getConfirmacion();
		if(!contrasena.equals(confirmacion)) {
			//input, clave, mensaje
			result.rejectValue("confirmacion", "Matches", "Las contraseñas no coinciden");
		}
		
		//Revisamos que el correo que recibimos NO exista en mi BD
		String email = nuevoUsuario.getEmail();
		Usuario existeUsuario = ru.findByEmail(email);
		if(existeUsuario != null) {
			//El correo ya está registrado
			result.rejectValue("email", "Unique", "El correo ingresado ya se encuentra registrado.");
		}
		
		//Si existe error, entonces regresamos null
		if(result.hasErrors()) {
			return null;
		} else {
			//Si NO hay error GUARDAMOS!
			//Encriptamos contraseña
			String contra_encriptada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
			nuevoUsuario.setPassword(contra_encriptada);
			return ru.save(nuevoUsuario);
		}
		
	}
	
	public Usuario login(String email, String password) {
		//Revisamos que el correo que recibimos esté en BD
		Usuario usuarioInicioSesion = ru.findByEmail(email); //Objeto Usuario o null
		//Obj(Usuario) nombre: Elena, apellido: De Troya, password: $2sas9mnasoasla
		if(usuarioInicioSesion == null) {
			return null;
		}
		
		//Comparamos contraseñas
		//BCrypt.checkpw(Contra NO encriptada, Contra SI encriptada) -> 
		//TRUE todo correcto, FALSE si no coinciden
		if(BCrypt.checkpw(password, usuarioInicioSesion.getPassword())) {
			return usuarioInicioSesion;
		}
		
		return null;
		
	}

}
