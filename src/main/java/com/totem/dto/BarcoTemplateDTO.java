package com.totem.dto;

import java.util.List;

public class BarcoTemplateDTO {

	private Long id;
	private String nome;
	private String constanteCampo;
	private List<Long> atividadeList;
	private String imagem;

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

	public List<Long> getAtividadeList() {
		return atividadeList;
	}

	public void setAtividadeList(List<Long> atividadeList) {
		this.atividadeList = atividadeList;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	
}
