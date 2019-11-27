package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.ConvenioDao;
import br.com.sismed.domain.Convenio;
import br.com.sismed.repository.ConvenioRepository;

@Service 
public class ConvenioServiceImpl implements ConvenioService{
	
	@Autowired
	private ConvenioRepository cRepository;

	@Autowired
	private ConvenioDao dao;
	
	@Override
	@Transactional(readOnly = false)
	public void salvar(Convenio convenio) {
		dao.save(convenio);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void editar(Convenio convenio) {
		dao.update(convenio);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Convenio buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Convenio> BuscarTodos() {
		return cRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Convenio> ListarConvenio() {
		
		return dao.ListarConvenio();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Convenio> ListaComboBox(Long id) {
		
		return dao.ListaComboBox(id);
	}

	
	
	
	

}
