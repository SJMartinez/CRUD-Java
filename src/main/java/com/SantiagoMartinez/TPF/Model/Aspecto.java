package com.SantiagoMartinez.TPF.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity

public class Aspecto {
	
	@Id
	@GeneratedValue
	private long idAspecto;
	
	private int horas;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "idOrden")
	private Orden orden;
	

	@OneToMany(mappedBy = "aspecto", cascade=CascadeType.ALL)
	private List<Reparacion> listaIntermediaReparacion;


	public long getIdAspecto() {
		return idAspecto;
	}


	public void setIdAspecto(long idAspecto) {
		this.idAspecto = idAspecto;
	}


	public int getHoras() {
		return horas;
	}


	public void setHoras(int horas) {
		this.horas = horas;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Orden getOrden() {
		return orden;
	}


	public void setOrden(Orden orden) {
		this.orden = orden;
	}


	public List<Reparacion> getListaIntermediaReparacion() {
		return listaIntermediaReparacion;
	}


	public void setListaIntermediaReparacion(List<Reparacion> listaIntermediaReparacion) {
		this.listaIntermediaReparacion = listaIntermediaReparacion;
	}
}