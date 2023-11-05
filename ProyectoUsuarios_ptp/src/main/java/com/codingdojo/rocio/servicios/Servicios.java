package com.codingdojo.rocio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.rocio.modelos.Direccion;
import com.codingdojo.rocio.modelos.Hobby;
import com.codingdojo.rocio.modelos.Salon;
import com.codingdojo.rocio.modelos.Usuario;
import com.codingdojo.rocio.repositorio.RepositorioDirecciones;
import com.codingdojo.rocio.repositorio.RepositorioHobbies;
import com.codingdojo.rocio.repositorio.RepositorioSalones;
import com.codingdojo.rocio.repositorio.RepositorioUsuarios;

@Service
public class Servicios {
	
	@Autowired //no es una instancia
	private RepositorioUsuarios repoUsuarios;
	
	@Autowired
	private RepositorioDirecciones repoDir;
	
	@Autowired
	private RepositorioSalones repoSalones;
	
	@Autowired
	private RepositorioHobbies repoHobbies;
	
	//Método que regrese una lista con todos los usuarios
	public List<Usuario> todosUsuarios(){
		return repoUsuarios.findAll();
		
	}
		
		//Guardamos un usuario
		public Usuario guardarUsuario(Usuario nuevoUsuario) {
			return repoUsuarios.save(nuevoUsuario);
			
		}
		
		//Me regresa un usuario en base a su ID
		public Usuario buscarUsuario(Long id) {
			//SELECT * FROM usuarios WHERE id =<id>
			return repoUsuarios.findById(id).orElse(null);//me regresa un usuario y si no lo encuentra nulo
		}
		
		//borre usuario en base a su ID
		public void borrarUsuario(Long id) {
			repoUsuarios.deleteById(id);//DELETE FROM usuarios WHERE id = <id>
		}
		
		//recibir una palabra
		//palabra = "na"
		public List<Usuario>buscarPorNombre(String palabra){
			return repoUsuarios.findByNombreContaining(palabra);
		}
		
		public List<Usuario>top10(){
			return repoUsuarios.findTop10ByOrderByNombreAsc();
		}
		
		public Direccion guardarDireccion(Direccion nuevaDireccion) {
			return repoDir.save(nuevaDireccion);
		}
		
		public List<Usuario> usuarioSinDireccion(){
			return repoUsuarios.findByDireccionIdIsNull();
		}
		
		public List<Salon> todosSalones(){
			return repoSalones.findAll();
		}
		
		public List<Hobby> todosHobbies(){
			return repoHobbies.findAll();
		}
		
		public Hobby buscarHobby(Long id) {
			return repoHobbies.findById(id).orElse(null);
		}
		
		public void guardarUsuarioHobby(Long usuario_id, Long hobby_id) {
			//Obtenemos el objeto de usuario
			Usuario miUsuario = buscarUsuario(usuario_id);
			
			//Obtenemos el objeto de hobby
			Hobby miHobby = buscarHobby(hobby_id);
			
			//Lista de Hobbies del usuario
			List<Hobby> listaHobbies = miUsuario.getHobbies();
			listaHobbies.add(miHobby);
			
			/*
			 * List<Usuario> listaUsuarios = miHobby.getUsuarios();
			 * listaUsuarios.add(miUsuario);
			 * repoHobbies.save(miHobby);
			 */
			
			repoUsuarios.save(miUsuario); //Actualizamos usuario
			
		}
		
		public void quitarUsuarioHobby(Long usuario_id, Long hobby_id) {
			Usuario miUsuario = buscarUsuario(usuario_id);
			Hobby miHobby = buscarHobby(hobby_id);
			List<Hobby> listaHobbies = miUsuario.getHobbies();
			listaHobbies.remove(miHobby);
			repoUsuarios.save(miUsuario);
		}
	}

