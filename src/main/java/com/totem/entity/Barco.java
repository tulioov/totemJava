package com.totem.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "BARCO")
public class Barco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@Column(name = "COD_BARCO")
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_INICIO")
	private Date dtInicio;

	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_FIM")
	private Date dtFim;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_INICIO_PREVISTO")
	private Date dtInicioPrevisto;

	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_FIM_PREVISTO")
	private Date dtFimPrevisto;
	
	@Lob
	@Column(name = "imagem")
	private String imagem;
	
	@Column(name = "NOME")
	@NotEmpty(message = "Barco deve conter um nome.")	
	@Size(min = 3 , max = 250, message = "Nome deve conter minimo de 3 caracter")
	private String nome;
	
	@Column(name = "CONSTANTE_CAMPO", unique=true)
	@NotEmpty(message = "Barco deve conter uma constanteCampo.")	
	@Size(min = 3 , max = 250, message = "Contante Campo deve conter minimo de 3 caracter")
	private String constanteCampo;
	
	@Column(name = "HRS_BARCO_PREVISTA")
	private Long hrsBarcoPrevista;
	
	@Column(name = "HRS_BARCO_AUTOMATIZADA")
	private Long hrsBarcoAutomatizada;
	
	@Column(name = "HRS_BARCO_TRABALHADAS")
	private Long hrsBarcoTrabalhadas;
	
	@Column(name = "USUARIO_DELETE")
	private String usuarioDelete;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name = "DT_DELETE")
	private Date dtDelete;
	
	@Column(name = "STATUS")
	private String status;
	
	@JoinColumn(name = "ID_MONITORACAO")
	@ManyToMany
	private List<Monitoracao> monitoracao;
	
	@Transient
	private Long tempoDiasFabricao;
	
	@Transient
	private Long tempoHorasFabricao;
	
	public Long getTempoHorasFabricao() {
		if(dtInicioPrevisto == null || dtFimPrevisto == null) {
			return null;
		}
		long dt = (dtFimPrevisto.getTime() - dtInicioPrevisto.getTime());
		return 24*(dt / 86400000L);
	}
	
		
	public Long getTempoDiasFabricao() {
		if(dtInicioPrevisto == null || dtFimPrevisto == null) {
			return null;
		}
		long dt = (dtFimPrevisto.getTime() - dtInicioPrevisto.getTime());
		return dt / 86400000L;
	}

	public void setTempoDiasFabricao(Long tempoDiasFabricao) {
		this.tempoDiasFabricao = tempoDiasFabricao;
	}

	public List<Monitoracao> getMonitoracao() {
		return monitoracao;
	}


	public void setMonitoracao(List<Monitoracao> monitoracao) {
		this.monitoracao = monitoracao;
	}


	public Date getDtInicioPrevisto() {
		return dtInicioPrevisto;
	}

	public void setDtInicioPrevisto(Date dtInicioPrevisto) {
		this.dtInicioPrevisto = dtInicioPrevisto;
	}

	public Date getDtFimPrevisto() {
		return dtFimPrevisto;
	}

	public void setDtFimPrevisto(Date dtFimPrevisto) {
		this.dtFimPrevisto = dtFimPrevisto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
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


	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Long getHrsBarcoAutomatizada() {
		return hrsBarcoAutomatizada;
	}


	public void setHrsBarcoAutomatizada(Long hrsBarcoAutomatizada) {
		this.hrsBarcoAutomatizada = hrsBarcoAutomatizada;
	}


	public Long getHrsBarcoTrabalhadas() {
		return hrsBarcoTrabalhadas;
	}


	public void setHrsBarcoTrabalhadas(Long hrsBarcoTrabalhadas) {
		this.hrsBarcoTrabalhadas = hrsBarcoTrabalhadas;
	}


	public Long getHrsBarcoPrevista() {
		return hrsBarcoPrevista;
	}


	public void setHrsBarcoPrevista(Long hrsBarcoPrevista) {
		this.hrsBarcoPrevista = hrsBarcoPrevista;
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
	
}
