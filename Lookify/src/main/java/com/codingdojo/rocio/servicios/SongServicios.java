package com.codingdojo.rocio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.rocio.modelos.Song;
import com.codingdojo.rocio.repositorios.RepositorioCanciones;

@Service
public class SongServicios {
	
	@Autowired
    private RepositorioCanciones repoCanciones;
    
    // Obtener todas las canciones
    public List<Song> getAllSongs() {
        return (List<Song>) repoCanciones.findAll();
    }
    
    // Crear una canción
    public Song createSong(Song song) {
        return repoCanciones.save(song);
    }
    
    // Eliminar una canción por id
    public void deleteSong(Long id) {
        repoCanciones.deleteById(id);
    }
    
    // Obtener una canción por id
    public Song getSong(Long id) {
        return repoCanciones.findById(id).orElse(null);
    }

    
    // Ver las Top 10 de la base de datos
    public List<Song> getTopSongs() {
        return repoCanciones.findTop10ByOrderByRatingAsc();
    }
    
    //buscar canción por nombre del artista
    public List<Song>buscarPorNombre(String palabra){
		return repoCanciones.findByArtistContaining(palabra);
	}
}
