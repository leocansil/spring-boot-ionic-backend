package com.leocs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.leocs.cursomc.domain.Categoria;
import com.leocs.cursomc.domain.Produto;
import com.leocs.cursomc.repositories.CategoriaRepository;
import com.leocs.cursomc.repositories.ProdutoRepository;
import com.leocs.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	
	/* Notas de aula
	em categoriaService.

	import java.util.Optional;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

	*/
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo : " + Produto.class.getName()));
	}
	
	/* Notas de Aula
	 
	 Não usar repository.save para salvar lista, utilizar o comando abaixo
	 
	 repository.saveAll(Arrays.asList(cat1, cat2))
	 */
	
	/* Notas de Aula

	 Retornar Exceção:
	 
	import java.util.Optional;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo : " + Categoria.class.getName()
			));
	}
	 
	 */
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
//		return repo.search(nome, categorias, pageRequest);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
