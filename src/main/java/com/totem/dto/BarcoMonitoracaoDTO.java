package com.totem.dto;

import java.util.Date;

public class BarcoMonitoracaoDTO {
	
	
	private Long idBarco;
	private Long idAtividade;
	private String nfcId;
	private String acao;
	private Date dtInicioAtividade;
	
	
	public Date getDtInicioAtividade() {
		return dtInicioAtividade;
	}
	public void setDtInicioAtividade(Date dtInicioAtividade) {
		this.dtInicioAtividade = dtInicioAtividade;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getIdBarco() {
		return idBarco;
	}
	public void setIdBarco(Long idBarco) {
		this.idBarco = idBarco;
	}
	public Long getIdAtividade() {
		return idAtividade;
	}
	public void setIdAtividade(Long idAtividade) {
		this.idAtividade = idAtividade;
	}
	public String getNfcId() {
		return nfcId;
	}
	public void setNfcId(String nfcId) {
		this.nfcId = nfcId;
	}
}
