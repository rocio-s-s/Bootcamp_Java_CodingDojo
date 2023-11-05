package com.codingdojo.rocio.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Salon;

//por cada modelo un repositorio

@Repository
public interface RepositorioSalones extends CrudRepository<Salon, Long>{
	
	List<Salon> findAll(); //SELECT * FROM salones
	
	//JPA Queries dinámicos
}
