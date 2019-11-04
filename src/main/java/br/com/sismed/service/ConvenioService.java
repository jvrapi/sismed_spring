package br.com.sismed.service;

import java.util.List;


import br.com.sismed.domain.Convenio;

public interface ConvenioService {

	void salvar(Convenio convenio);
	void editar(Convenio convenio);
	void excluir(Long id);
	Convenio buscarPorId(Long id);
	
	List<Convenio> BuscarTodos();
	List<Convenio> ListarConvenio();
	
	List<Convenio> ListaComboBox(Long id);
	
}
