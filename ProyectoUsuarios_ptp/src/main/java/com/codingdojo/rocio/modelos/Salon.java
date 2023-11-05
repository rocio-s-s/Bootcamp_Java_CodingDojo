package com.codingdojo.rocio.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="salones")
public class Salon {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nombre;
	
	@Column(updatable=false)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@JsonManagedReference(value="salones-json")
	@OneToMany(mappedBy="salon", fetch=FetchType.LAZY)
	private List<Usuario> usuarios;

	public Salon() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
