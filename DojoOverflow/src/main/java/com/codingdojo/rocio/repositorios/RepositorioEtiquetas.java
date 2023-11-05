package com.codingdojo.rocio.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Etiqueta;

@Repository
public interface RepositorioEtiquetas extends CrudRepository <Etiqueta, Long> {
	
	List<Etiqueta> findAll();
	
	Etiqueta findByTema(String tema);
}
