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
	private RepositorioUsuarios repoUsuarios;
	
	/*método que me registre un nuevo usuario*/
	public Usuario registrar(Usuario nuevoUsuario, BindingResult result ) {
		//comparamos contraseñas
		String contrasena = nuevoUsuario.getPassword();
		String confirmacion = nuevoUsuario.getConfirmacion();
		
		if(!contrasena.equals(confirmacion)) {
			result.rejectValue("confirmacion", "Matches", "Las contraseñas no coinciden");
		}
		
		//revisamos que el correo que recibimos NO exista en mi BD
		String email = nuevoUsuario.getEmail();
		Usuario existeUsuario=repoUsuarios.findByEmail(email);
		
		if(existeUsuario !=null) {
			//el correo ya está registrado
			result.rejectValue("email", "Unique", "El correo ingresado ya se encuentra registrado");
		}
		
		//si existe error, entonces regresamos null
		if(result.hasErrors()) {
			return null;
		}else {
			//si NO existe error GUARDAMOS
			//encriptamos contraseña
			String contra_encriptada = BCrypt.hashpw(contrasena, BCrypt.gensalt());
			nuevoUsuario.setPassword(contra_encriptada);
			return repoUsuarios.save(nuevoUsuario);
		}
		
	}
	
	public Usuario login(String email, String password) {
		//revisamos que el correo que recibimos está en la base de datos
		Usuario usuarioInicioSesion = repoUsuarios.findByEmail(email);
		
		if(usuarioInicioSesion == null) {
			return null;
		}
		
		//comparamos contraseñas
		//BCryp.checkpw(contra NO encriptada, contra SI encriptada)
		//TRUE todo correscto o FALSE si no coinciden
		if(BCrypt.checkpw(password, usuarioInicioSesion.getPassword())) {
			return usuarioInicioSesion;
		}else {
			return null;
		}
	}

}
