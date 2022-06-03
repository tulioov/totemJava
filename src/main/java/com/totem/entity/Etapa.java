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
@Table(name = "ETAPA")
public class Etapa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_ETAPA")
	private Long id;
	
	@Column(name = "DESCRICAO")
	@NotEmpty(message = "Etapa deve conter uma descrição.")	
	@Size(min = 3 , max = 250, message = "descrição deve conter minimo de 3 caracter")
	private String descricao;
	
	@Column(name = "CONSTANTE_CAMPO")
	@NotEmpty(message = "Constante campo deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Constante campo deve conter minimo de 3 caracter")
	private String constanteCampo;
	
	
	@JoinColumn(name = "ID_ATIVIDADE")
	@OneToMany
	private List<Atividade> atividadeList;
	
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

	public List<Atividade> getAtividadeList() {
		return atividadeList;
	}

	public void setAtividadeList(List<Atividade> atividadeList) {
		this.atividadeList = atividadeList;
	}
	
}
