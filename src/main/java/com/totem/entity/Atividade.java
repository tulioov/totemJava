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
@Table(name = "ATIVIDADE")
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD_ATIVIDADE")
	private Long id;
	
	@Column(name = "DESCRICAO")
	@NotEmpty(message = "Atividade deve conter uma descrição.")	
	@Size(min = 3 , max = 250, message = "Descrição deve conter minimo de 3 caracter")
	private String descricao;
	
	@Column(name = "CONSTANTE_CAMPO")
	@NotEmpty(message = "Atividade deve conter uma constanteCampo.")	
	@Size(min = 3 , max = 250, message = "Contante Campo deve conter minimo de 3 caracter")
	private String constanteCampo;
	
	@JoinColumn(name = "ID_SUB_ATIVIDADE")
	@OneToMany
	private List<SubAtividade> subAtividadeList;
	
	public List<SubAtividade> getSubAtividadeList() {
		return subAtividadeList;
	}

	public void setSubAtividadeList(List<SubAtividade> subAtividadeList) {
		this.subAtividadeList = subAtividadeList;
	}

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

}
