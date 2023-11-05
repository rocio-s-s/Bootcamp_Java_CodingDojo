package com.codingdojo.rocio.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="tareas")
public class Tarea {
	
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AI
	private Long id;
	
	@NotEmpty(message="El título es obligatorio")
	private String titulo;
	
	@NotEmpty(message="El campo Prioridad es obligatorio")
	private String prioridad;
	
	@NotEmpty(message="El campo Asignatario es obligatorio")
	private String asignatario;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	//creacion del proyecto SOLO 1 usuario puede crear la tarea
	//usuario puede crear muchas tareas
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario creador; //usuario que crea la tarea
	
	
	public Tarea() {
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getPrioridad() {
		return prioridad;
	}



	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	public Usuario getCreador() {
		return creador;
	}



	public void setCreador(Usuario creador) {
		this.creador = creador;
	}



	public String getAsignatario() {
		return asignatario;
	}


	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}


	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	

}
