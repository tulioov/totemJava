package com.totem.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	@Column(name = "DT_FIM_ATIVIDADE_TOTAL")
	private Date dtFimAtividadeTotal;

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
	
	@ManyToMany(targetEntity=MonitoracaoAtividade.class)
	private Set<MonitoracaoAtividade> monitoracaoAtividade;
	
	public Set<MonitoracaoAtividade> getMonitoracaoAtividade() {
		return monitoracaoAtividade;
	}

	public void setMonitoracaoAtividade(Set<MonitoracaoAtividade> monitoracaoAtividade) {
		this.monitoracaoAtividade = monitoracaoAtividade;
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

}
