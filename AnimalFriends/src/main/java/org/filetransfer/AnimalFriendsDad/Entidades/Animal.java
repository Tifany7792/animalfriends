package org.filetransfer.AnimalFriendsDad.Entidades;

import java.io.File;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	@Column
	private String tipo;
	@Column
	private String descripcion;

	@Lob
	@JsonIgnore
	private File imageFile;

	public Animal() {

	}

	public Animal(String tipo, String descripcion,File image) {
		super();
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.imageFile = image;
	}

	public long getId() {
		return this.Id;
	}

	public void setId(long id2) {
		this.Id = id2;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String t) {
		this.tipo = t;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String d) {
		this.descripcion = d;
	}

	public File getImageFile() {
		return imageFile;
	}
	
	/*public void setImage(String image) {
		
		this.imageFile = image;
	}

	public void setImageFile(Blob imageFile) {
		this.imageFile = imageFile;
	}*/

	@Override
	public String toString() {
		return "Animal [tipo=" + tipo + ", descripcion=" + descripcion + "]";
	}

}
