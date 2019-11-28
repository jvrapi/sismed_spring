package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.RegistroClinico;
import br.com.sismed.repository.RClinicoRepository;

@Service @Transactional
public class RegistroClinicoService{
	
	@Autowired
	private RClinicoRepository rcRepository;

	
	
	
	public void salvar(RegistroClinico registro_clinico) {
		rcRepository.save(registro_clinico);
	}

	

	
	public void excluir(Long id) {
		rcRepository.deleteById(id);
	}


	@Transactional(readOnly = true)
	public RegistroClinico buscarporId(Long id) {
		
		return rcRepository.getOne(id);
	}

	
	@Transactional(readOnly = true)
	public List<RegistroClinico> buscarTodos() {
		
		return rcRepository.findAll();
	}
	
	
	public Page<RegistroClinico> ListarRegPac(Long id, Pageable pageable) {
		return rcRepository.ListarRegPac(id, pageable);
	}
	

	public Page<RegistroClinico> ListarRegAgenda(Long id, Pageable pageable) {
		return rcRepository.ListarRegAgenda(id, pageable);
	}
}