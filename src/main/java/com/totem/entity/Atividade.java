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
@Table(name = "ATIVIDADE")
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_ATIVIDADE")
	private Long id;
	
	@Column(name = "CODIGO", unique=true)
	private String codigo;

	@Column(name = "NOME", unique=true)
	@NotEmpty(message = "Atividade deve conter um nome")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;

	@Column(name = "CONSTANTE_CAMPO", unique=true)
	@NotEmpty(message = "Atividade deve conter uma contante campo.")
	@Size(min = 3, max = 250, message = "A deve conter minimo de 3 caracter")
	private String constanteCampo;
	
	@Column(name = "DESCRICAO", unique=true)
	@NotEmpty(message = "Nome deve conter uma descrição")	
	@Size(min = 3 , max = 250, message = "Descrição deve conter minimo de 3 caracter")
	private String descricao;

	@Column(name = "TEMPO_ESTIMADO")
	private String tempoEstimado;
	
	@Column(name = "TEMPO_REAL")
	private String tempoReal;
	
	@Column(name = "USUARIO_DELETE")
	private String usuarioDelete;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_DELETE")
	private Date dtDelete;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_INSERT")
	private Date DtInsert;
	

	public Date getDtInsert() {
		return DtInsert;
	}

	public void setDtInsert(Date dtInsert) {
		DtInsert = dtInsert;
	}

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

	public String getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(String tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
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

	public String getTempoReal() {
		return tempoReal;
	}

	public void setTempoReal(String tempoReal) {
		this.tempoReal = tempoReal;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
