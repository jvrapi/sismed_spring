package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Laboratorio;
import br.com.sismed.repository.LaboratorioRepository;

@Repository
@Transactional(readOnly = false)
public class LaboratorioService {

	@Autowired
	private LaboratorioRepository lRepository;
	
	
	public void salvar(Laboratorio laboratorio) {
		lRepository.save(laboratorio);
	}

	
	public void excluir(Long id) {
		lRepository.deleteById(id);
	}

	
	@Transactional(readOnly = true)
	public Laboratorio buscarporId(Long id) {
		
		return lRepository.getOne(id);
	}

	
	@Transactional(readOnly = true)
	public Page<Laboratorio> buscarTodos(Pageable pageable) {
		
		return lRepository.findAll(pageable);
	}

	public List<Laboratorio> ListarLaboratorioNome(String dado) {
		return lRepository.ListarLaboratorioNome(dado);
		
	}

	public Laboratorio ListarLaboratorioId(String dado) {
		return lRepository.ListarLaboratorioId(dado);
	}


	public List<Laboratorio> ListarLaboratorioTelefone(String dado) {
		return lRepository.ListarLaboratorioTelefone(dado);
	}


	public List<Laboratorio> ListarLaboratorioBairro(String dado) {
		return lRepository.ListarLaboratorioBairro(dado);
	}
	
	public List<Laboratorio> ListarLabTConv(Long id) {
		return lRepository.ListarLabTConv(id);
	}
	
	public List<Laboratorio> findAll() {
		return lRepository.findAll();
	}
}
