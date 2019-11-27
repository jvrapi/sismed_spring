package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Procedimento;

public interface ProcedimentoService {

	void salvar(Procedimento procedimento);
	void excluir(Long id);
	
	Procedimento BuscarPorId(Long id);
	
	List<Procedimento> BuscarTodos();
	
	List<Procedimento> ListarProcedimento(Long id);

	
}
