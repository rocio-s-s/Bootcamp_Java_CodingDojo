package com.codingdojo.rocio.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Usuario;

@Repository
public interface RepositorioUsuarios extends CrudRepository<Usuario, Long>{
	
	//SELECT * FROM usuarios WHERER email = EMAIL QUE RECIBIMOS	
	Usuario findByEmail(String email);

}
