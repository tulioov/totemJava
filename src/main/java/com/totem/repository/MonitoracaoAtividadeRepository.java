package com.totem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.MonitoracaoAtividade;

@Repository
public interface MonitoracaoAtividadeRepository extends CrudRepository<MonitoracaoAtividade, Long> {

}
