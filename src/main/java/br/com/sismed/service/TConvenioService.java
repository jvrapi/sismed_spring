package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Convenio;
import br.com.sismed.domain.TConvenio;
import br.com.sismed.repository.TConvenioRepository;

@Service 
public class TConvenioService {
	
	@Autowired
	private TConvenioRepository tcRepository;

	
	
	@Transactional(readOnly = false)
	public void salvar(TConvenio tconvenio) {
		tcRepository.save(tconvenio);
		
	}


	
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		tcRepository.deleteById(id);
		
	}

	
	@Transactional(readOnly = true)
	public TConvenio buscarPorId(Long id) {
		
		return  tcRepository.getOne(id) ;
	}

	
	@Transactional(readOnly = true)
	public List<TConvenio> findAll() {
		return tcRepository.findAll();
	}


	@Transactional(readOnly = true)
	public Page<TConvenio> ListarTipoConvenio(Long id, Pageable pageable ) {
		
		return tcRepository.ListarTipoConvenio(id, pageable);
	}

	@Transactional(readOnly = true)
	public List<TConvenio> ListaComboBox(Long id) {
		return tcRepository.ListaComboxBox(id);
	}

	@Transactional(readOnly = true)
	public List<TConvenio> ListaCad(Long id) {
		return tcRepository.ListaCad(id);
	}
	
	@Transactional(readOnly = true)
	public List<TConvenio> ListarPorNome(String dado) {
		return tcRepository.ListarPorNome(dado);
	}
	
	public List<TConvenio> BuscarTConvenioLab(Long id) {
		return tcRepository.BuscarTConvenioLab(id);
	}
	
}
