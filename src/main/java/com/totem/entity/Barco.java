package com.totem.entity;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "BARCO")
public class Barco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_BARCO")
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_INICIO")
	private Date dtInicio;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_FIM")
	private Date dtFim;
	
	@Lob
	@Column(name = "imagem")
	private String imagem;
	
	@Column(name = "NOME")
	@NotEmpty(message = "Barco deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;
	
	@Column(name = "DESCRICAO")
	@NotEmpty(message = "Barco deve conter uma descrição.")	
	@Size(min = 3 , max = 250, message = "descrição deve conter minimo de 3 caracter")
	private String descricao;
	
	@ManyToMany(targetEntity=Monitoracao.class)
	private Set<?> Monitoracao;
	
	public Set<?> getMonitoracao() {
		return Monitoracao;
	}

	public void setMonitoracao(Set<?> monitoracao) {
		Monitoracao = monitoracao;
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
