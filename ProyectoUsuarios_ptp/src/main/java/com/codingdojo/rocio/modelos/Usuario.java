package com.codingdojo.rocio.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuarios")//nombre de tablas y clases en plural, en tabla solo minunsculas
public class Usuario {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull //@NotEmpy no puede estar vacío
	@Size(min=2, max=100, message="El nombre debe de tener al menos 2 caracteres")
	private String nombre;
	
	@NotNull
	@Size(min=2, max=100)
	private String apellido;
	
	@NotNull
	@Size(min=2, max=200)
	private String email;
	
	@NotNull
	@Size(min=2, max=255)//en un futuro vamos a encriptarla
	private String password;
	
	@Column(updatable=false)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@JsonManagedReference(value="direcciones-json")
	@OneToOne(mappedBy="usuario", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Direccion direccion;
	
	@JsonBackReference(value="salones-json")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="salon_id")//llave foránea
	private Salon salon;
	
	@JsonManagedReference(value="hobbies-json")
	@JsonIgnore //para NO mandarlo a través de JSON al ser guardado
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="usuarios_tienen_hobbies",
			joinColumns = @JoinColumn(name="usuario_id"),//id de la clase en la que estoy
			inverseJoinColumns = @JoinColumn(name="hobby_id")//id de la contraparte
			)
	private List<Hobby> hobbies;
	

	public Usuario() {
	}
	//crear nuevo usuario
	public Usuario(String nombre, String apellido, String email, String password) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}
	//actualizar usuario
	public Usuario(long id, String nombre, String apellido, String email, String password) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}
	//getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
	
	public Salon getSalon() {
		return salon;
	}
	public void setSalon(Salon salon) {
		this.salon = salon;
	}
	
	
	
	
	public List<Hobby> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
	
	@PrePersist //antes de hacer la creación
	protected void onCreate() {
		this.createdAt = new Date();
		} //DEFAULT CURRENT_TIMESTAMP
		
	protected void onUpdate() {
		this.updatedAt = new Date();
		} //DEFAULT CURRENT_TIMESTAMP
			
	
}
