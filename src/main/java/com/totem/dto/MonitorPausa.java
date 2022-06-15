package com.totem.dto;

import com.totem.entity.Barco;
import com.totem.entity.Monitoracao;

public class MonitorPausa {
	
	private Barco barco;
	private Monitoracao monitoracao;
	private String status;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public Monitoracao getMonitoracao() {
		return monitoracao;
	}

	public void setMonitoracao(Monitoracao monitoracao) {
		this.monitoracao = monitoracao;
	}
	
}
