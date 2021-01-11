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

public class Auto {
	

	@Id
	@GeneratedValue
	private long idAuto;
	
	private String modelo;
	private String patente;
	
	@ManyToOne
	@JoinColumn(name = "idDuenio")
	private Duenio duenio;
	

	@OneToMany(mappedBy = "auto", cascade=CascadeType.ALL)
	private List<Orden> listaIntermediaOrden;

	public long getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(long idAuto) {
		this.idAuto = idAuto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Duenio getDuenio() {
		return duenio;
	}

	public void setDuenio(Duenio duenio) {
		this.duenio = duenio;
	}

	public List<Orden> getListaIntermediaOrden() {
		return listaIntermediaOrden;
	}

	public void setListaIntermediaOrden(List<Orden> listaIntermediaOrden) {
		this.listaIntermediaOrden = listaIntermediaOrden;
	}

}
