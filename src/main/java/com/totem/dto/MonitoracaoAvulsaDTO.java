package com.totem.dto;

import java.util.Date;

public class MonitoracaoAvulsaDTO {

	private Long idMonitoracao;
	private Long idUsuario;
	private Long idBarco;
	private Long idFase;
	private Long idLocal;
	private Long idAtividade;
	private Date dtInicioAtividade;
	private Date dtFimAtividade;

	
	
	public Long getIdMonitoracao() {
		return idMonitoracao;
	}

	public void setIdMonitoracao(Long idMonitoracao) {
		this.idMonitoracao = idMonitoracao;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(Long idBarco) {
		this.idBarco = idBarco;
	}

	public Long getIdFase() {
		return idFase;
	}

	public void setIdFase(Long idFase) {
		this.idFase = idFase;
	}

	public Long getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}

	public Long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
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
