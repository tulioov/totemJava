package com.totem.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_USUARIO")
	private Long id;

	
	@Column(name = "NOME")
	@NotEmpty(message = "Usuario deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;
	
	@Column(name = "ESPECIALIDADE")
	@NotEmpty(message = "Usuario deve conter uma especialidade.")	
	@Size(min = 3 , max = 250, message = "Especialidade deve conter minimo de 3 caracter")
	private String especialidade;
	
	@Column(name = "COD_RFID")
	private String codRfid;
	
	@Column(name = "isAdmin")
	private Boolean isAdmin;
	
	@JoinColumn(name = "ID_ETAPA")
	@OneToMany
	private List<Etapa> etapaList;
	
	public List<Etapa> getEtapaList() {
		return etapaList;
	}

	public void setEtapaList(List<Etapa> etapaList) {
		this.etapaList = etapaList;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCodRfid() {
		return codRfid;
	}

	public void setCodRfid(String codRfid) {
		this.codRfid = codRfid;
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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

}
