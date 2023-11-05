package com.codingdojo.rocio.modelos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="canciones")
public class Song {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @Size(min = 5, message = "El título debe tener al menos 5 caracteres")
    private String title;
    
    @Column
    @Size(min = 5, message = "El artista debe tener al menos 5 caracteres")
    private String artist;
    
    @Column
    @Min(value = 1, message = "La clasificación no puede ser menor a 1")
    @Max(value = 10, message = "La clasificación no puede superar 10")
    private Integer rating;
    
    @Column(updatable=false)
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date updatedAt;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
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

	@PrePersist //antes de hacer la creación
	protected void onCreate() {
		this.createdAt = new Date();
		} //DEFAULT CURRENT_TIMESTAMP
		
	protected void onUpdate() {
		this.updatedAt = new Date();
		} //DEFAULT CURRENT_TIMESTAMP

	public Song() {
	}
    
    

}
