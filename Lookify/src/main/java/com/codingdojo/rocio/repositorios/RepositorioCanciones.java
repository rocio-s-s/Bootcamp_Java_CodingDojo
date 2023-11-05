package com.codingdojo.rocio.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.rocio.modelos.Song;

@Repository
public interface RepositorioCanciones extends CrudRepository<Song, Long> {
	
	List<Song>findByArtistContaining(String palbra);

	List<Song> findTop10ByOrderByRatingAsc();

}
