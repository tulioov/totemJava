package com.totem.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD_USUARIO")
	private Long id;

	@Column(name = "NOME")
	@NotEmpty(message = "Nome deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;
	
	@Column(name = "ESPECIALIDADE")
	@NotEmpty(message = "Usuario deve conter uma especialidade.")	
	@Size(min = 3 , max = 250, message = "Especialidade deve conter minimo de 3 caracter")
	private String especialidade;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "MATRICULA")
	private String matricula;
	
	@Column(name = "USUARIO_DELETE")
	private String usuarioDelete;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_DELETE")
	private Date dtDelete;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "COD_RFID")
	private String codRfid;
	
	@Column(name = "isAdmin")
	private Boolean isAdmin;
	
	@Column(name = "isLider")
	private Boolean isLider;
	
	@JoinColumn(name = "ID_USUARIO")
	@ManyToMany
	private List<Fase> faseList;
	
	@Transient
	private String nomeBarco;
	
	public String getNomeBarco() {
		return nomeBarco;
	}

	public void setNomeBarco(String nomeBarco) {
		this.nomeBarco = nomeBarco;
	}

	public List<Fase> getFaseList() {
		return faseList;
	}

	public void setFaseList(List<Fase> faseList) {
		this.faseList = faseList;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsuarioDelete() {
		return usuarioDelete;
	}

	public void setUsuarioDelete(String usuarioDelete) {
		this.usuarioDelete = usuarioDelete;
	}

	public Date getDtDelete() {
		return dtDelete;
	}

	public void setDtDelete(Date dtDelete) {
		this.dtDelete = dtDelete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsLider() {
		return isLider;
	}

	public void setIsLider(Boolean isLider) {
		this.isLider = isLider;
	}

}
