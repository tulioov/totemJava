package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.BarcoMonitoracao;

@Repository
public interface BarcoMonitoracaoRepository extends CrudRepository<BarcoMonitoracao, Long> {

	List<BarcoMonitoracao> findAll();
}
