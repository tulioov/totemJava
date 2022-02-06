package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.SubAtividade;

@Repository
public interface SubAtividadeRepository extends CrudRepository<SubAtividade, Long> {

	List<SubAtividade> findAll();
}
