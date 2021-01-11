package com.SantiagoMartinez.TPF.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class Orden {

	@Id
	@GeneratedValue
	private long idOrden;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Sesiones sesiones;
	
	@ManyToOne
	@JoinColumn(name = "idAuto")
	private Auto auto;
	

	@OneToMany(mappedBy = "orden", cascade=CascadeType.ALL)
	private List<Aspecto> listaIntermediaAspecto;

	
	private String descripcion;
	private String fecha;
	private boolean abierta;
	
	
	
	public long getIdOrden() {
		return idOrden;
	}
	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}
	public Sesiones getSession() {
		return sesiones;
	}
	public void setSession(Sesiones sesiones) {
		this.sesiones = sesiones;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public Sesiones getSesiones() {
		return sesiones;
	}
	public void setSesiones(Sesiones sesiones) {
		this.sesiones = sesiones;
	}
	public Auto getAuto() {
		return auto;
	}
	public void setAuto(Auto auto) {
		this.auto = auto;
	}
	public List<Aspecto> getListaIntermediaAspecto() {
		return listaIntermediaAspecto;
	}
	public void setListaIntermediaAspecto(List<Aspecto> listaIntermediaAspecto) {
		this.listaIntermediaAspecto = listaIntermediaAspecto;
	}
	public boolean isAbierta() {
		return abierta;
	}
	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}

}
