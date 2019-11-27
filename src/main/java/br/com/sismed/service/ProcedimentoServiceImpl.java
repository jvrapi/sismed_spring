package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Procedimento;
import br.com.sismed.repository.ProcedimentoRepository;

@Service 
public class ProcedimentoServiceImpl implements ProcedimentoService{

	
	@Autowired
	private ProcedimentoRepository pRepository;
	
	@Override
	@Transactional(readOnly = false)
	public void salvar(Procedimento procedimento) {
		pRepository.save(procedimento);
		
	}


	@Override
	@Transactional(readOnly = true)
	public Procedimento BuscarPorId(Long id) {
		
		return pRepository.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Procedimento> BuscarTodos() {
		
		return pRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		pRepository.deleteById(id);
		
	}

	@Override
	public List<Procedimento> ListarProcedimento(Long id) {
		
		return pRepository.ListarProcedimento(id);
	}


	

}
