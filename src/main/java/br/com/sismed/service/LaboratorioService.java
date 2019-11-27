package br.com.sismed.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.sismed.domain.Laboratorio;

@Service
public interface LaboratorioService {
	
	void salvar(Laboratorio laboratorio);
	void excluir(Long id);
	
	Laboratorio buscarporId(Long id);
	
	Page<Laboratorio> buscarTodos(Pageable pageable);
	
}
