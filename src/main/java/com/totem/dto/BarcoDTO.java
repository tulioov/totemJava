package com.totem.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BarcoDTO {
	
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dtInicio;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dtFim;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dtInicioPrevisto;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dtFimPrevisto;
	
	private String imagem;
	
	private Long hrsBarcoPrevista;
	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;
	
	@NotEmpty(message = "Barco deve conter uma descrição.")	
	@Size(min = 3 , max = 250, message = "descrição deve conter minimo de 3 caracter")
	private String descricao;
	
	private Set<?> monitoracao;
	
	

	public Long getHrsBarcoPrevista() {
		return hrsBarcoPrevista;
	}

	public void setHrsBarcoPrevista(Long hrsBarcoPrevista) {
		this.hrsBarcoPrevista = hrsBarcoPrevista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Date getDtInicioPrevisto() {
		return dtInicioPrevisto;
	}

	public void setDtInicioPrevisto(Date dtInicioPrevisto) {
		this.dtInicioPrevisto = dtInicioPrevisto;
	}

	public Date getDtFimPrevisto() {
		return dtFimPrevisto;
	}

	public void setDtFimPrevisto(Date dtFimPrevisto) {
		this.dtFimPrevisto = dtFimPrevisto;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<?> getMonitoracao() {
		return monitoracao;
	}

	public void setMonitoracao(Set<?> monitoracao) {
		this.monitoracao = monitoracao;
	}

}
