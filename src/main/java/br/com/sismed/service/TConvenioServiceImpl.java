package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.TConvenioDao;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.repository.TConvenioRepository;

@Service 
public class TConvenioServiceImpl implements TConvenioService{
	
	@Autowired
	private TConvenioRepository tcRepository;

	@Autowired
	private TConvenioDao dao;
	
	@Override
	@Transactional(readOnly = false)
	public void salvar(TConvenio tconvenio) {
		tcRepository.save(tconvenio);
		
	}


	@Override
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		tcRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public TConvenio buscarPorId(Long id) {
		
		return  tcRepository.getOne(id) ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TConvenio> BuscarTodos() {
		return tcRepository.findAll();
	}

	@Override
	public List<TConvenio> ListarTipoConvenio(Long id) {
		
		return tcRepository.ListarTipoConvenio(id);
	}

	@Override
	public List<TConvenio> ListaComboBox(Long id) {
		return tcRepository.ListaComboxBox(id);
	}

	@Override
	public List<TConvenio> ListaCad(Long id) {
		return tcRepository.ListaCad(id);
	}

}
