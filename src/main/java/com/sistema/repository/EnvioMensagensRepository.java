package com.sistema.repository;

import org.springframework.data.repository.CrudRepository;

import com.sistema.models.EnvioMensagens;

public interface EnvioMensagensRepository extends CrudRepository<EnvioMensagens, String>{
	EnvioMensagens findById(int id);
}
