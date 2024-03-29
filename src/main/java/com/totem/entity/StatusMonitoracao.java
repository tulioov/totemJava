package com.totem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "STATUS_MONITORACAO")
public class StatusMonitoracao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_STATUS_MONITORACAO")
	private Long id;
	
	@Column(name = "NOME")
	@NotEmpty(message = "Nome deve conter um nome")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;
	
	@Column(name = "CONSTANTE_CAMPO", unique=true)
	@NotEmpty(message = "Constante campo deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Constante campo deve conter minimo de 3 caracter")
	private String constanteCampo;
	

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
	
}
