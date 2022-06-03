package com.totem.dto;

public class BarcoMonitoracaoDTO {
	
	
	private Long idBarco;
	private Long idSubAtividade;
	private String nfcId;
	public Long getIdBarco() {
		return idBarco;
	}
	public void setIdBarco(Long idBarco) {
		this.idBarco = idBarco;
	}
	public Long getIdSubAtividade() {
		return idSubAtividade;
	}
	public void setIdSubAtividade(Long idSubAtividade) {
		this.idSubAtividade = idSubAtividade;
	}
	public String getNfcId() {
		return nfcId;
	}
	public void setNfcId(String nfcId) {
		this.nfcId = nfcId;
	}
}
