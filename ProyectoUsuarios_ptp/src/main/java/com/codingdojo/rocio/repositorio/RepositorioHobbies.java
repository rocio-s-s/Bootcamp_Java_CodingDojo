package com.codingdojo.rocio.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Hobby;

@Repository
public interface RepositorioHobbies extends CrudRepository<Hobby, Long> {

	List<Hobby> findAll(); //SELECT * FROM hobbies
}
