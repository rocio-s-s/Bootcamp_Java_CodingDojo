package com.codingdojo.rocio.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Usuario;

//CRUD = Create Read Update Delete
@Repository
public interface RepositorioUsuarios extends CrudRepository<Usuario, Long>{
	
	List<Usuario> findAll(); //SELECT * FROM usuarios
	
	//INSERT INTO usuarios (nombre, apellido...) VALUE(Atributos del objeto usuario)
	//UPDATE usuarios SET nombre = valor de objeto
	Usuario save(Usuario nuevoUsuario); //recibimos objeto usuario
	
	//Queries dinámicos
	//SELECT * FROM usuarios WHERE email =<email>
	List<Usuario> findByEmail(String email);
	
	//SELECT * FROM usuarios WHERE email =<nombre que recibimos>
		List<Usuario> findByNombre(String nombre);
		
		//SELECT * FROM usuarios WHERE nombre LIKE "<letras>%"
		List<Usuario> findByNombreStartingWith(String letras);
		
		//SELECT * FROM usuarios WHERE nombre LIKE "%<palabra>%"
		List<Usuario>findByNombreContaining(String palbra);
		
		//SELECT * FROM usuarios ORDER BY nombre ASC -> rating DESC
		List<Usuario>findTop10ByOrderByNombreAsc();
		
		//Regrese los usuarios que no tienen dirección
		List<Usuario> findByDireccionIdIsNull();

		

}
