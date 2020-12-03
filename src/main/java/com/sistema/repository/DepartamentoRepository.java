package com.sistema.repository;

import org.springframework.data.repository.CrudRepository;

import com.sistema.models.Departamento;

public interface DepartamentoRepository extends CrudRepository<Departamento, String> {
	Departamento findById (long id);
}
