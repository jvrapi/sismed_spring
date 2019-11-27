package br.com.sismed.service;

import java.util.List;


import br.com.sismed.domain.TConvenio;

public interface TConvenioService {


	void salvar(TConvenio convenio);
	
	void excluir(Long id);
	TConvenio buscarPorId(Long id);
	
	List<TConvenio> BuscarTodos();
	List<TConvenio> ListarTipoConvenio(Long id);
	List<TConvenio> ListaComboBox(Long id);
	List<TConvenio> ListaCad(Long id);
}
