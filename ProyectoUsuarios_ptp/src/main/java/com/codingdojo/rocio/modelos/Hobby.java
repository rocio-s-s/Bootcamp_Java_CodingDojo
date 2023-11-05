package com.codingdojo.rocio.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="hobbies")
public class Hobby {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String actividad;
	
	@Column(updatable=false)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@JsonBackReference(value="hobbies-json")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="usuarios_tienen_hobbies",
			joinColumns = @JoinColumn(name="hobby_id"),//id de la clase en la que estoy
			inverseJoinColumns = @JoinColumn(name="usuario_id")//id de la contraparte
			)
	private List<Usuario> usuarios;

	public Hobby() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
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
	
	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PrePersist //antes de hacer la creación
	protected void onCreate() {
		this.createdAt = new Date();
		} //DEFAULT CURRENT_TIMESTAMP
		
	protected void onUpdate() {
		this.updatedAt = new Date();
		} //DEFAULT CURRENT_TIMESTAMP
}
