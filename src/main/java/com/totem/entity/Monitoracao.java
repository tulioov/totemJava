package com.totem.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "MONITORACAO")
public class Monitoracao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD_MONITORACAO")
	private Long id;
	
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_INICIO_ATIVIDADE")
	private Date dtInicioAtividade;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_FIM_ATIVIDADE")
	private Date dtFimAtividade;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_INICIO_ATIVIDADE_TOTAL")
	private Date dtInicioAtividadeTotal;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_FIM_ATIVIDADE_TOTAL")
	private Date dtFimAtividadeTotal;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_BARCO", referencedColumnName = "COD_BARCO")
	private Barco barco;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "COD_USUARIO")
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ETAPA", referencedColumnName = "COD_ETAPA")
	private Etapa etapa;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ATIVIDADE", referencedColumnName = "COD_ATIVIDADE")
	private Atividade atividade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_SUBATIVIDADE", referencedColumnName = "COD_SUB_ATIVIDADE")
	private SubAtividade subAtividade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtInicioAtividade() {
		return dtInicioAtividade;
	}

	public void setDtInicioAtividade(Date dtInicioAtividade) {
		this.dtInicioAtividade = dtInicioAtividade;
	}

	public Date getDtFimAtividade() {
		return dtFimAtividade;
	}

	public void setDtFimAtividade(Date dtFimAtividade) {
		this.dtFimAtividade = dtFimAtividade;
	}

	public Date getDtInicioAtividadeTotal() {
		return dtInicioAtividadeTotal;
	}

	public void setDtInicioAtividadeTotal(Date dtInicioAtividadeTotal) {
		this.dtInicioAtividadeTotal = dtInicioAtividadeTotal;
	}

	public Date getDtFimAtividadeTotal() {
		return dtFimAtividadeTotal;
	}

	public void setDtFimAtividadeTotal(Date dtFimAtividadeTotal) {
		this.dtFimAtividadeTotal = dtFimAtividadeTotal;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public SubAtividade getSubAtividade() {
		return subAtividade;
	}

	public void setSubAtividade(SubAtividade subAtividade) {
		this.subAtividade = subAtividade;
	}

}
