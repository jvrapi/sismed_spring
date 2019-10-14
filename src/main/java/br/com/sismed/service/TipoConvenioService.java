package br.com.sismed.service;

import java.util.List;

import br.com.sismed.domain.TipoConvenio;

public interface TipoConvenioService {

	void salvar(TipoConvenio tipoconvenio);
	void editar(TipoConvenio tipo_convenio);
	void deletar(Long id);
	
	TipoConvenio BuscarPorID(Long id);
	List<TipoConvenio> BuscarTodos();
}
