package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Agenda;
import br.com.sismed.domain.RegistroClinico;

public interface RegistroClinicoService {
	
	void salvar(RegistroClinico registro_clinico);
	void editar(RegistroClinico registro_clinico);
	void excluir(Long id);
	
	RegistroClinico buscarporId(Long id);
	
	List<RegistroClinico> buscarTodos();

	List<RegistroClinico> ListarRegPaciente(Long id);
}
