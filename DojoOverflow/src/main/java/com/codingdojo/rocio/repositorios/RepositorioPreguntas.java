package com.codingdojo.rocio.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Pregunta;

@Repository
public interface RepositorioPreguntas extends CrudRepository <Pregunta, Long>{
	
	List<Pregunta> findAll();
	
	List<Pregunta> findByEtiquetasTemaContaining(String tema);

}
