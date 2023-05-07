package com.totem.dto;

import java.util.List;

import com.totem.entity.Atividade;
import com.totem.entity.Local;
import com.totem.entity.Usuario;

public class ImportDTO {
	
	List<Usuario> lstUsuario;
	
	List<Atividade> lstAtividade;
	
	List<Local> lstLocal;
	
	String tipoObject;
	

	public List<Atividade> getLstAtividade() {
		return lstAtividade;
	}

	public void setLstAtividade(List<Atividade> lstAtividade) {
		this.lstAtividade = lstAtividade;
	}

	public List<Local> getLstLocal() {
		return lstLocal;
	}

	public void setLstLocal(List<Local> lstLocal) {
		this.lstLocal = lstLocal;
	}

	public String getTipoObject() {
		return tipoObject;
	}

	public void setTipoObject(String tipoObject) {
		this.tipoObject = tipoObject;
	}

	public List<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(List<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}
	
	

}
