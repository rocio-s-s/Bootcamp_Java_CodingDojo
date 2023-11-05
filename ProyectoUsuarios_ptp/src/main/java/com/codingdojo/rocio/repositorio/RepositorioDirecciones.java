package com.codingdojo.rocio.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Direccion;

@Repository
public interface RepositorioDirecciones extends CrudRepository<Direccion, Long> {
	
	List<Direccion> findAll();

}
