package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Barco;
import com.totem.entity.Monitoracao;

@Repository
public interface MonitoracaoRepository extends CrudRepository<Monitoracao, Long> {

	List<Monitoracao> findAll();

	Monitoracao findByBarco(Barco barco);
}
