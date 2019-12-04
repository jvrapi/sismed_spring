package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Convenio;
import br.com.sismed.repository.ConvenioRepository;

@Service 
public class ConvenioService{
	
	
	@Autowired
	private ConvenioRepository cRepository;

	
	
	
	@Transactional(readOnly = false)
	public void salvar(Convenio convenio) {
		cRepository.save(convenio);
		
	}

	

	
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		cRepository.deleteById(id);
		
	}

	
	@Transactional(readOnly = true)
	public Convenio buscarPorId(Long id) {
		
		return cRepository.getOne(id);
	}

	
	@Transactional(readOnly = true)
	public Page<Convenio> BuscarTodos(Pageable pageable) {
		return cRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Convenio> findAll() {
		return cRepository.findAll();
	}

	
	
	@Transactional(readOnly = true)
	public List<Convenio> ListaComboBox(Long id) {
		
		return cRepository.ListaComboBox(id);
	}

	@Transactional(readOnly = true)
	public List<Convenio> ListarPorNome(String dado) {
		
		return cRepository.ListarPorNome(dado);
	}
	@Transactional(readOnly = true)
	public List<Convenio> ListarPorCNPJ(String dado) {
		
		return cRepository.ListarPorCNPJ(dado);
	}
	
	@Transactional(readOnly = true)
	public List<Convenio> ListarPorANS(String dado) {
		
		return cRepository.ListarPorANS(dado);
	}

	
	
	
	

}
