package com.codingdojo.rocio.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Proyecto;
import com.codingdojo.rocio.modelos.Usuario;

@Repository
public interface RepositorioProyectos extends CrudRepository<Proyecto, Long> {
	
	//lista de proyectos que incluyan un usuario
	List<Proyecto> findByUsuariosUnidosContains(Usuario usuario);
	
	//lista de proyectos que NO incluyan a una persona
	List<Proyecto> findByUsuariosUnidosNotContains(Usuario usuario);

}
