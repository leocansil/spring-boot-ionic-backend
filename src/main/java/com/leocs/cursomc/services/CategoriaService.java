package com.leocs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leocs.cursomc.domain.Categoria;
import com.leocs.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	/* Notas de aula
	em categoriaService.

	import java.util.Optional;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

	*/
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	/* Notas de Aula
	 
	 Não usar repository.save para salvar lista, utilizar o comando abaixo
	 
	 repository.saveAll(Arrays.asList(cat1, cat2))
	 */
}
