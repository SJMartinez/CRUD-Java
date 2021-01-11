package com.SantiagoMartinez.TPF.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;



@Entity
public class Repuesto {

	@Id
	@GeneratedValue
	private long idRepuesto;
	
	private double coste;
	
	
	private String descripcion;
	
	@OneToMany(mappedBy = "repuesto", cascade=CascadeType.ALL)
	private List<Reparacion> listaIntermediaRepuesto;



	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Reparacion> getListaIntermediaRepuesto() {
		return listaIntermediaRepuesto;
	}

	public void setListaIntermediaRepuesto(List<Reparacion> listaIntermediaRepuesto) {
		this.listaIntermediaRepuesto = listaIntermediaRepuesto;
	}

	public long getIdRepuesto() {
		return idRepuesto;
	}

	public void setIdRepuesto(long idRepuesto) {
		this.idRepuesto = idRepuesto;
	}
}