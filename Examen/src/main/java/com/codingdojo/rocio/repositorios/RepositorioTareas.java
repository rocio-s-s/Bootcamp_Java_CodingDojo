package com.codingdojo.rocio.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Tarea;

@Repository
public interface RepositorioTareas extends CrudRepository <Tarea, Long> {
	
	 List<Tarea> findAll();
	 
	 
}
