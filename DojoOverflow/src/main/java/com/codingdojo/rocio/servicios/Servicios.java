package com.codingdojo.rocio.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.rocio.modelos.Etiqueta;
import com.codingdojo.rocio.modelos.Pregunta;
import com.codingdojo.rocio.modelos.Respuesta;
import com.codingdojo.rocio.repositorios.RepositorioEtiquetas;
import com.codingdojo.rocio.repositorios.RepositorioPreguntas;
import com.codingdojo.rocio.repositorios.RepositorioRespuestas;

@Service
public class Servicios {
	
	@Autowired
	private RepositorioPreguntas repoPreguntas;
	
	@Autowired
	private RepositorioRespuestas repoRespuestas;
	
	@Autowired
	private RepositorioEtiquetas repoEtiquetas;
	
	public List<Pregunta> todasPreguntas(){
		return repoPreguntas.findAll();
		}
	
	public Pregunta guardarPregunta(Pregunta nuevaPregunta) {
		return repoPreguntas.save(nuevaPregunta);
	}
	
	public Respuesta guardarRespuesta(Respuesta nuevaRespuesta) {
		return repoRespuestas.save(nuevaRespuesta);
	}
	
	public Etiqueta encuentraEtiqueta(String tema) {
		return repoEtiquetas.findByTema(tema);
	}
	
	public Etiqueta guardarEtiqueta(Etiqueta nuevaEtiqueta) {
		return repoEtiquetas.save(nuevaEtiqueta);
	}
	
	public Pregunta encuentraPregunta(Long id) {
		return repoPreguntas.findById(id).orElse(null);
	}
}
