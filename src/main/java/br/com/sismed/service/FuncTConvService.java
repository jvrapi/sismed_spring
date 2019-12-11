package br.com.sismed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.FuncTConv;
import br.com.sismed.repository.FuncTConvRepository;

@Service
public class FuncTConvService {
	
	@Autowired
	private FuncTConvRepository ftcRepository;

	@Transactional(readOnly = false)
	public void salvar(FuncTConv functconv) {
		ftcRepository.save(functconv);
	}
}
