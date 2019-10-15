package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.TipoConvenio;

public interface TipoConvenioService {

	void salvar(TipoConvenio convenio);
	void editar(TipoConvenio convenio);
	void excluir(Long id);
	TipoConvenio buscarPorId(Long id);
	
	List<TipoConvenio> BuscarTodos();
}
