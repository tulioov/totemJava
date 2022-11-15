package com.totem.dto;

import java.util.Date;
import java.util.List;

public class FiltroPesquisaMonitoracaoDTO {
	
	private List<Long> usuarioLstId;
	private Date dataInidio;
	private Date dataFim;

	public List<Long> getUsuarioLstId() {
		return usuarioLstId;
	}

	public void setUsuarioLstId(List<Long> usuarioLstId) {
		this.usuarioLstId = usuarioLstId;
	}

	public Date getDataInidio() {
		return dataInidio;
	}

	public void setDataInidio(Date dataInidio) {
		this.dataInidio = dataInidio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
}
