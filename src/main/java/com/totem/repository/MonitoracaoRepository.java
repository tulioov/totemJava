package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Monitoracao;
import com.totem.entity.Usuario;

@Repository
public interface MonitoracaoRepository extends CrudRepository<Monitoracao, Long> {

	List<Monitoracao> findAll();
	
	List<Monitoracao> findByUsuario(Usuario usuario);

	Monitoracao findByUsuarioAndStatusEqualsAndDtFimAtividadeTotalIsNull(Usuario usuario, String status);
	
	List<Monitoracao> findByIdBarco(Long idBarco);
	
}

