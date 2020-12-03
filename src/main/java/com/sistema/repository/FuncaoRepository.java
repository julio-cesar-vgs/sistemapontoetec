package com.sistema.repository;

import org.springframework.data.repository.CrudRepository;

import com.sistema.models.Funcao;

public interface FuncaoRepository extends CrudRepository<Funcao, String> {
	Funcao findById (long id);
}
