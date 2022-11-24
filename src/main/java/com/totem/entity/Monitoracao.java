package com.totem.entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "MONITORACAO")
public class Monitoracao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COD_MONITORACAO")
	private Long id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_INICIO_ATIVIDADE")
	private Date dtInicioAtividade;

	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Column(name = "DT_FIM_ATIVIDADE")
	private Date dtFimAtividade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_STATUS_MONITORACAO", referencedColumnName = "COD_STATUS_MONITORACAO")
	private StatusMonitoracao statusMonitoracao;
	
	@Column(name = "ID_BARCO")
	private Long idBarco;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "COD_USUARIO")
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_FASE", referencedColumnName = "COD_FASE")
	private Fase fase;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_LOCAL", referencedColumnName = "COD_LOCAL")
	private Local local;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ATIVIDADE", referencedColumnName = "COD_ATIVIDADE")
	private Atividade atividade;
	
	@Transient
	private Long tempoTrabalho;
	
	@Transient
	private Long tempoTrabalhoFimIni;
	
	public Long getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(Long idBarco) {
		this.idBarco = idBarco;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	public Long getTempoTrabalho() {
		long dt = (new Date().getTime() - dtInicioAtividade.getTime());
		return (dt *24*60) / 86400000L;
	}
	
	private String addZeroUmDigits(Long numero) {
		
		String n = Long.toString(numero);
		return (n.length()<2)?"0"+numero:Long.toString(numero);
	}
	
	public String getTempoTrabalhoFimIni() {
		
		if(dtInicioAtividade != null && dtFimAtividade != null) {
			long dt = (dtFimAtividade.getTime() - dtInicioAtividade.getTime());
			
			String days_difference = addZeroUmDigits(TimeUnit.MILLISECONDS.toDays(dt) % 365);  
			String years_difference = addZeroUmDigits(TimeUnit.MILLISECONDS.toDays(dt) / 365l);  
			String seconds_difference = addZeroUmDigits(TimeUnit.MILLISECONDS.toSeconds(dt) % 60);  
			String minutes_difference = addZeroUmDigits(TimeUnit.MILLISECONDS.toMinutes(dt) % 60);   
			String hours_difference = addZeroUmDigits(TimeUnit.MILLISECONDS.toHours(dt) % 24);   
			
			return hours_difference + ":"+ minutes_difference + ":"+ seconds_difference + " - " + days_difference + "/" +years_difference ;
			//return (dt *24*60) / 86400000L;
		}
		return null;
		
	}
	
	public Date getDtInicioAtividade() {
		return dtInicioAtividade;
	}

	public void setDtInicioAtividade(Date dtInicioAtividade) {
		this.dtInicioAtividade = dtInicioAtividade;
	}

	public Date getDtFimAtividade() {
		return dtFimAtividade;
	}

	public void setDtFimAtividade(Date dtFimAtividade) {
		this.dtFimAtividade = dtFimAtividade;
	}

	public StatusMonitoracao getStatusMonitoracao() {
		return statusMonitoracao;
	}

	public void setStatusMonitoracao(StatusMonitoracao statusMonitoracao) {
		this.statusMonitoracao = statusMonitoracao;
	}
	
}
