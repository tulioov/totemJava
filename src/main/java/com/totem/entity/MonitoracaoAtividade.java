package com.totem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "MONITORACAO_ATIVIDADE")
public class MonitoracaoAtividade {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_MONITORACAO_ATIVIDADE")
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_INICIO_ATIVIDADE")
	private Date dtInicioAtividade;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_FIM_ATIVIDADE")
	private Date dtFimAtividade;
	
	@Transient
	private Long tempoTrabalho;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
