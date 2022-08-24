package com.totem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.totem.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	List<Usuario> findAll();
	
	Usuario findByEmail(String email);
	
	Usuario findByCodRfid(String nfc);

	Usuario findBycodRfid(String codRfid);
}
