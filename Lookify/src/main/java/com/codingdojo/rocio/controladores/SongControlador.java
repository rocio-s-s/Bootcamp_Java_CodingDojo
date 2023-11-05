package com.codingdojo.rocio.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.rocio.modelos.Song;
import com.codingdojo.rocio.servicios.SongServicios;

import jakarta.validation.Valid;

@Controller
public class SongControlador {
	
	@Autowired
    private SongServicios songService;
	
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}
    
    // Mostrar todas las canciones en una tabla
    @GetMapping("/dashboard")
    public String index(Model model) {
        List<Song> songs = songService.getAllSongs();
        model.addAttribute("songs", songs);
        return "dashboard.jsp";
    }
    
    // Mostrar el formulario para agregar una canción
    @GetMapping("/new")
    public String newSong(@ModelAttribute("song") Song song) {
        return "new.jsp";
    }
    
    // Procesar el formulario y crear una canción
    @PostMapping("/crear")
    public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult result) {
        if (result.hasErrors()) {
            return "new.jsp";
        } else {
            songService.createSong(song);
            return "redirect:/dashboard";
        }
    }
    
    @GetMapping("/show/{id}")
	public String mostrar(@PathVariable("id")Long id, 
						Model model) {
		
		//obtener un objeto de usuario en base al ID
		Song cancionBuscada = songService.getSong(id);
		model.addAttribute("song",cancionBuscada);
		
		return "show.jsp";
	}
    
    // Eliminar una canción por id
    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable("id") Long id) {
        songService.deleteSong(id);
        return "redirect:/dashboard";
    }
    
    
    //Buscar por nombre de artista
    @GetMapping("/buscar/{palabra}")
	public String buscar(@PathVariable("palabra")String palabra,
						Model model) {
		List<Song> cancionConPalabra = songService.buscarPorNombre(palabra);
		model.addAttribute("songs",cancionConPalabra);
		
		model.addAttribute("palabra", palabra);
		
		return "search.jsp";
	}
	
	@PostMapping("/busqueda")
	public String busqueda(@RequestParam(value="palabra")String palabra) {
		return "redirect:/buscar/"+palabra;
	}
    // Mostrar las Top 10 de la base de datos
    @GetMapping("/top")
    public String topSongs(Model model) {
        List<Song> songs = songService.getTopSongs();
        model.addAttribute("songs", songs);
        return "top.jsp";
    }

}
