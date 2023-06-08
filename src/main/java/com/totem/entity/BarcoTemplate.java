package com.totem.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BARCO_TEMPLATE")
public class BarcoTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_BARCO_TEMPLATE")
	private Long id;

	@Column(name = "imagem")
	private String imagem;

	@Column(name = "NOME", unique = true)
	@NotEmpty(message = "Nome deve conter um nome")
	@Size(min = 3, max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;

	@Column(name = "CONSTANTE_CAMPO", unique = true)
	@NotEmpty(message = "Constante campo deve conter um nome.")
	@Size(min = 3, max = 250, message = "Constante campo deve conter minimo de 3 caracter")
	private String constanteCampo;

	@JoinColumn(name = "ID_BARCO_TEMPLATE")
	@ManyToMany
	private List<Atividade> atividadeList;

	@Column(name = "USUARIO_DELETE")
	private String usuarioDelete;

	@JsonFormat(pattern = "dd/MM/yyyy")
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

	public List<Atividade> getAtividadeList() {
		return atividadeList;
	}

	public void setAtividadeList(List<Atividade> atividadeList) {
		this.atividadeList = atividadeList;
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
}
