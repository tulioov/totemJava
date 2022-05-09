package com.totem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BARCO_MONITORACAO")
public class BarcoMonitoracao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD_BARCO_MONITORACAO")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_BARCO", referencedColumnName = "COD_BARCO")
	private Barco barco;
	
	@JoinColumn(name = "ID_MONITORACAO")
	@OneToMany
	private List<Monitoracao> monitoracaoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public List<Monitoracao> getMonitoracaoList() {
		return monitoracaoList;
	}

	public void setMonitoracaoList(List<Monitoracao> monitoracaoList) {
		this.monitoracaoList = monitoracaoList;
	}

}
