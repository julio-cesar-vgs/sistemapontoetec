package com.example.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.persistence.metamodel.StaticMetamodel;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriasRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriasRepository categoriasRepository;

	@GetMapping
	public List<Categoria> listar() {
		return categoriasRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriasSalva = categoriasRepository.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{codigo}")
				.buildAndExpand(categoriasSalva.getCodigo()).toUri();
		response.setHeader("location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(categoriasSalva);

	}

	@GetMapping("/{codigo}")
	public Categoria buscaPeloCodigo(@PathVariable Long codigo) {
		return categoriasRepository.findOne(codigo);
	}
}
