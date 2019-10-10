package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.TipoConvenioDao;
import br.com.sismed.domain.TipoConvenio;

@Service
public class TipoConvenioServiceImpl implements TipoConvenioService{

	
	@Autowired
	private TipoConvenioDao dao;
	
	@Override
	public void salvar(TipoConvenio tipoconvenio) {
		dao.save(tipoconvenio);
		
	}

	@Override
	 @Transactional(readOnly = false)
	public void editar(TipoConvenio tipo_convenio) {
		dao.update(tipo_convenio);
		
	}

	@Override
	 @Transactional(readOnly = false)
	public void deletar(Long id) {
		dao.delete(id);
		
	}

	@Override
	 @Transactional(readOnly = true)
	public TipoConvenio findById(Long id) {
		
		return dao.findById(id);
	}

	@Override
	 @Transactional(readOnly = true)
	public List<TipoConvenio> findAll() {
		
		return dao.findAll();
	}

}
