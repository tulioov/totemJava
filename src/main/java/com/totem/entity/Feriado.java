package com.totem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "FERIADO")
public class Feriado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_FERIADO")
	private Long id;
	
	@Column(name = "DESCRICAO")
	@NotEmpty(message = "Etapa deve conter uma descrição.")	
	@Size(min = 3 , max = 250, message = "descrição deve conter minimo de 3 caracter")
	private String descricao;
	
	@Column(name = "CONSTANTE_CAMPO")
	@NotEmpty(message = "Constante campo deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Constante campo deve conter minimo de 3 caracter")
	private String constanteCampo;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_FERIADO")
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
