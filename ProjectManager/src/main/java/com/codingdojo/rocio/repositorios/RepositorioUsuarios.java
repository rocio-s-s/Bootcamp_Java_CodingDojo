package com.codingdojo.rocio.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.rocio.modelos.Usuario;

public interface RepositorioUsuarios extends CrudRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);

}
