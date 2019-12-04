package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Procedimento;
import br.com.sismed.repository.ProcedimentoRepository;

@Service 
public class ProcedimentoService {

	
	@Autowired
	private ProcedimentoRepository pRepository;
	
	
	@Transactional(readOnly = false)
	public void salvar(Procedimento procedimento) {
		pRepository.save(procedimento);
		
	}


	
	@Transactional(readOnly = true)
	public Procedimento BuscarPorId(Long id) {
		
		return pRepository.getOne(id);
	}

	
	@Transactional(readOnly = true)
	public List<Procedimento> BuscarTodos() {
		
		return pRepository.findAll();
	}

	
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		pRepository.deleteById(id);
		
	}

	@Transactional(readOnly = true)
	public List<Procedimento> ListarProcedimento(Long id) {
		
		return pRepository.ListarProcedimento(id);
	}


	@Transactional(readOnly = true)
	public List<Procedimento> ListarPorDescricao(String dado) {
		
		return pRepository.ListarPorDescricao(dado);
	}

}
