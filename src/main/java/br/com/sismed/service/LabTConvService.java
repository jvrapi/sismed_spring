package br.com.sismed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.LabTConv;
import br.com.sismed.repository.LabTConvRepository;

@Service
public class LabTConvService {

	@Autowired
	private LabTConvRepository ltcRepository;
	
	public void salvar(LabTConv ltc) {
		ltcRepository.save(ltc);
	}
	
	public void excluir(Long id) {
		ltcRepository.deleteById(id);
	}
}
