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
	
	@Column(name = "NOME", unique=true)
	@NotEmpty(message = "Nome deve conter um nome")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;
	
	@Column(name = "CONSTANTE_CAMPO", unique=true)
	@NotEmpty(message = "Constante campo deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Constante campo deve conter minimo de 3 caracter")
	private String constanteCampo;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_FERIADO")
	private Date dtFeriado;
	
	@Column(name = "USUARIO_DELETE")
	private String usuarioDelete;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_DELETE")
	private Date dtDelete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	public String getUsuarioDelete() {
		return usuarioDelete;
	}

	public void setUsuarioDelete(String usuarioDelete) {
		this.usuarioDelete = usuarioDelete;
	}

	public Date getDtDelete() {
		return dtDelete;
	}

	public void setDtDelete(Date dtDelete) {
		this.dtDelete = dtDelete;
	}
	
}
