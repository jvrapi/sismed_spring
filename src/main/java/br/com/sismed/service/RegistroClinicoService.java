package br.com.sismed.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sismed.domain.RegistroClinico;

public interface RegistroClinicoService {
	
	void salvar(RegistroClinico registro_clinico);
	void editar(RegistroClinico registro_clinico);
	void excluir(Long id);
	
	RegistroClinico buscarporId(Long id);
	
	List<RegistroClinico> buscarTodos();
	
	Page<RegistroClinico> ListarRegPac(Long id, Pageable pageable);
	Page<RegistroClinico> ListarRegAgenda(Long id, Pageable pageable);
}
