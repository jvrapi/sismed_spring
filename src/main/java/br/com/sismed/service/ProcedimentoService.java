package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.Procedimento;

public interface ProcedimentoService {

	void salvar(Procedimento procedimento);
	void editar(Procedimento procedimento);
	void excluir(Long id);
	void deletar(Long id);
	
	Procedimento BuscarPorId(Long id);
	
	List<Procedimento> BuscarTodos();

	
}
