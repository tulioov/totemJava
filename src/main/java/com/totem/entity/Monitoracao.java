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
import javax.persistence.Transient;

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
	@Column(name = "DT_FIM_ATIVIDADE_TOTAL")
	private Date dtFimAtividadeTotal;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ID_BARCO")
	private Long idBarco;

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
	
	@Transient
	private Long tempoTrabalho;
	
	
	
	public Long getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(Long idBarco) {
		this.idBarco = idBarco;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtFimAtividadeTotal() {
		return dtFimAtividadeTotal;
	}

	public void setDtFimAtividadeTotal(Date dtFimAtividadeTotal) {
		this.dtFimAtividadeTotal = dtFimAtividadeTotal;
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
	
	public Long getTempoTrabalho() {
		long dt = (new Date().getTime() - dtInicioAtividade.getTime());
		return dt / 86400000L;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
