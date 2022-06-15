package com.totem.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FeriadoDTO {

	private Long id;
	private String descricao;
	private String constanteCampo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dtFeriado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConstanteCampo() {
		return constanteCampo;
	}

	public void setConstanteCampo(String constanteCampo) {
		this.constanteCampo = constanteCampo;
	}

	public Date getDtFeriado() {
		return dtFeriado;
	}

	public void setDtFeriado(Date dtFeriado) {
		this.dtFeriado = dtFeriado;
	}

}
