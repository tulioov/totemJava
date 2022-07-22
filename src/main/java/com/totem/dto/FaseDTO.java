package com.totem.dto;

import java.util.List;

public class FaseDTO {
	
	private Long id;
	private String nome;
	private String constanteCampo;
	private List<Long> localList;
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
	public List<Long> getLocalList() {
		return localList;
	}
	public void setLocalList(List<Long> localList) {
		this.localList = localList;
	}

}
