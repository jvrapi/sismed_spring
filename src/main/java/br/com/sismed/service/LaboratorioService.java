package br.com.sismed.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sismed.domain.Laboratorio;

@Service
public interface LaboratorioService {
	
	void salvar(Laboratorio laboratorio);
	void editar(Laboratorio laboratorio);
	void excluir(Long id);
	
	Laboratorio buscarporId(Long id);
	
	List<Laboratorio> buscarTodos();

}
