package com.totem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_USUARIO")
	private Long id;

	
	@Column(name = "NOME")
	@NotNull(message = "Usuario deve conter um nome.")
	@NotEmpty(message = "Usuario deve conter um nome.")	
	@Size(min = 3 , max = 100, message = "Nome deve conter minimo de 3 caracater")
	private String nome;
	
	@Column(name = "ESPECIALIDADE")
	@NotNull(message = "Usuario deve conter uma especialidade.")
	@NotEmpty(message = "Usuario deve conter uma especialidade.")	
	@Size(min = 3 , max = 100, message = "Especialidade deve conter minimo de 3 caracater")
	private String especialidade;

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
