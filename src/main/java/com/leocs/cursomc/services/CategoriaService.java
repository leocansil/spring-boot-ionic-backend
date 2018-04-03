package com.leocs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.leocs.cursomc.domain.Categoria;
import com.leocs.cursomc.repositories.CategoriaRepository;
import com.leocs.cursomc.services.exceptions.DataIntegrityException;
import com.leocs.cursomc.services.exceptions.ObjectNotFoundException;

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
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo : " + Categoria.class.getName()));
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
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try{
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
}
