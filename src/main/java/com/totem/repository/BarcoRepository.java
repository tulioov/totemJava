package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Barco;

@Repository
public interface BarcoRepository extends CrudRepository<Barco, Long> {

	List<Barco> findAll();
}
