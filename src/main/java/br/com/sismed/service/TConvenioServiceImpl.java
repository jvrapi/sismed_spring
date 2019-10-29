package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.TConvenioDao;
import br.com.sismed.domain.TConvenio;

@Service 
public class TConvenioServiceImpl implements TConvenioService{

	@Autowired
	private TConvenioDao dao;
	
	@Override
	@Transactional(readOnly = false)
	public void salvar(TConvenio tconvenio) {
		dao.save(tconvenio);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void editar(TConvenio tconvenio) {
		dao.update(tconvenio);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public TConvenio buscarPorId(Long id) {
		
		return  dao.findById(id) ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TConvenio> BuscarTodos() {
		return dao.findAll();
	}

	@Override
	public List<TConvenio> ListarTipoConvenio(Long id) {
		
		return dao.ListarTipoConvenio(id);
	}

	@Override
	public List<TConvenio> ListaComboBox(Long id) {
		
		return dao.ListaComboBox(id);
	}

}
