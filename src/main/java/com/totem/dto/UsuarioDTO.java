package com.totem.dto;

import java.util.List;

public class UsuarioDTO {
	
	private Long id;
	private String username;
	private String password;
	private String nome;
	private String especialidade;
	private String email;
	private String matricula;
	private String codRfid;
	private Boolean isAdmin;
	private Boolean isLider;
	private List<Long> faseList;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getCodRfid() {
		return codRfid;
	}
	public void setCodRfid(String codRfid) {
		this.codRfid = codRfid;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public Boolean getIsLider() {
		return isLider;
	}
	public void setIsLider(Boolean isLider) {
		this.isLider = isLider;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public List<Long> getFaseList() {
		return faseList;
	}
	public void setFaseList(List<Long> faseList) {
		this.faseList = faseList;
	}
}
