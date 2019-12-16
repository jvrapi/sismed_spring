package br.com.sismed.service;

import java.util.List;

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
	
	@Transactional(readOnly = false)
	public void salvarList(List<FuncTConv> functconv) {
		ftcRepository.saveAll(functconv);
	}
	
	@Transactional(readOnly = false)
	public void excluirList(List<FuncTConv> functconv) {
		ftcRepository.deleteAll(functconv);
	}
	
	@Transactional
	public void deleteTConvFunc(Long id, Long funcId) {
		ftcRepository.deleteTConvFunc(id, funcId);
	}
}
