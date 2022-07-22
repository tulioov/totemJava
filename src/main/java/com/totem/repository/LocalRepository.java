package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Local;

@Repository
public interface LocalRepository extends CrudRepository<Local, Long> {

	List<Local> findAll();
}
