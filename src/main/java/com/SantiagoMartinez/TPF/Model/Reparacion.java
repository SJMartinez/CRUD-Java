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

public class Reparacion {
	
	@Id
	@GeneratedValue
	private long idReparacion;
	

	private int cantidad;


	@ManyToOne
	@JoinColumn(name = "idRepuesto")
	private Repuesto repuesto;

	@ManyToOne
	@JoinColumn(name = "idAspecto")
	private Aspecto aspecto;
	

	public long getIdReparacion() {
		return idReparacion;
	}

	public void setIdReparacion(long idReparacion) {
		this.idReparacion = idReparacion;
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Aspecto getAspecto() {
		return aspecto;
	}

	public void setAspecto(Aspecto aspecto) {
		this.aspecto = aspecto;
	}
	
}
