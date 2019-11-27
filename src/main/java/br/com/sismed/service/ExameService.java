package br.com.sismed.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sismed.domain.Exame;

public interface ExameService {
	
	void salvar(Exame exame);
	void excluir(Long id);
	
	Exame buscarporId(Long id);
	
	Page<Exame> buscarTodos(Pageable pageable);
	
	
}
