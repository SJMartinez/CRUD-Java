package com.SantiagoMartinez.TPF.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;





@Entity
public class Sesiones {

	@Id 
	private String usuario;
	
	@OneToMany( mappedBy = "sesiones" )
	private List<Orden> listaSesion;
	
	

	private String contrasenia;


	


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Orden> getListaSesion() {
		return listaSesion;
	}

	public void setListaSesion(List<Orden> listaSesion) {
		this.listaSesion = listaSesion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	
}