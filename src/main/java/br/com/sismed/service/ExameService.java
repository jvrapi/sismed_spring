package br.com.sismed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Exame;
import br.com.sismed.repository.ExameRepository;

@Service
@Transactional(readOnly = false)
public class ExameService {
	
	@Autowired
	private ExameRepository eRepository;
	
	
	public void salvar(Exame exame) {
		eRepository.save(exame);
	}

	
	public void excluir(Long id) {
		eRepository.deleteById(id);;
	}

	
	@Transactional(readOnly = true)
	public Exame buscarporId(Long id) {
		return eRepository.getOne(id);
	}

	 
	@Transactional(readOnly = true)
	public Page<Exame> buscarTodos(Pageable pageable) {
	
		return eRepository.findAll(pageable);
	}
}
