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
@Table(name = "ITEM")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_ITEM")
	private Long id;
	
	@Column(name = "DESCRICAO")
	@NotEmpty(message = "Barco deve conter uma descrição.")	
	@Size(min = 3 , max = 250, message = "descrição deve conter minimo de 3 caracter")
	private String descricao;
	
	@Column(name = "CONSTANTE_CAMPO")
	@NotEmpty(message = "Barco deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String constanteCampo;
	
	@Column(name = "QUANTIDADE")
	@NotEmpty(message = "Barco deve conter um nome.")	
	private Integer quantidade;
	
	@Column(name = "UNIDADE_MEDIDA")
	@NotEmpty(message = "Barco deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String unidadeMedida;

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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	
}
