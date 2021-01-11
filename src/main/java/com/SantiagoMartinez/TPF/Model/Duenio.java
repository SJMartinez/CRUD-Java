package com.SantiagoMartinez.TPF.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity

public class Duenio {

	@Id
	@GeneratedValue
	private long idDuenio;
	
	private String nombre;
	private String apellido;
	private String direccion;
	private long telefono;
	
	@OneToMany(mappedBy = "duenio", cascade=CascadeType.ALL)
	private List<Auto> listaIntermediaAuto;

	public long getIdDuenio() {
		return idDuenio;
	}

	public void setIdDuenio(long idDuenio) {
		this.idDuenio = idDuenio;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public List<Auto> getListaIntermediaAuto() {
		return listaIntermediaAuto;
	}

	public void setListaIntermediaAuto(List<Auto> listaIntermediaAuto) {
		this.listaIntermediaAuto = listaIntermediaAuto;
	}
}
