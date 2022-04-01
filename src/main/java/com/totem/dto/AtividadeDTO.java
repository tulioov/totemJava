package com.totem.dto;

import java.util.List;

public class AtividadeDTO {
	
	private Long id;
	
	private String descricao;
	
	private String constanteCampo;
	
	private List<Long> subAtividadeList;

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

	public List<Long> getSubAtividadeList() {
		return subAtividadeList;
	}

	public void setSubAtividadeList(List<Long> subAtividadeList) {
		this.subAtividadeList = subAtividadeList;
	}

}
