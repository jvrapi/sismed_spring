package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Exame;
import br.com.sismed.repository.ExameRepository;

@Service
@Transactional(readOnly = false)
public class ExameService {
	
	@Autowired
	private ExameRepository eRepository;
	
	
	public void salvar(Exame exame) {
		eRepository.save(exame);
	}

	
	public void excluir(Long id) {
		eRepository.deleteById(id);;
	}

	
	@Transactional(readOnly = true)
	public Exame buscarporId(Long id) {
		return eRepository.getOne(id);
	}

	@Transactional(readOnly = true)
	public Page<Exame> buscarTodos(Pageable pageable) {
	
		return eRepository.findAll(pageable);
	}

	public List<Exame> ListarExamePaciente(String dado) {
		return eRepository.ListarExamePaciente(dado);
	}


	public List<Exame> ListarExameNome(String dado) {
		return eRepository.ListarExameNome(dado);
	}


	public List<Exame> ListarExameDataColeta(String dado) {
		return eRepository.ListarExameDataColeta(dado);
	}
	
	public List<Exame> ListaExamePacienteId(Long id) {
		return eRepository.BuscarExamePacienteId(id);
	}
	
	public List<Exame> BuscarExamePorNome(String exame) {
		return eRepository.BuscarExamePorNome(exame);
	}
	
	public List<Exame> ListarExameData(String data) {
		return eRepository.ListarExameData(data);
	}
	
	public List<Exame> ListarExamePacienteExame(Long id, String exame) {
		return eRepository.ListarExamePacienteExame(id, exame);
	}
	
	public List<Exame> ListarExamePacienteData(Long id, String data) {
		return eRepository.ListarExamePacienteData(id, data);
	}
	
	public List<Exame> ListarExameExameData(String exame, String data) {
		return eRepository.ListarExameExameData(exame, data);
	}
	
	public List<Exame> ListarExameTudo(Long id, String exame, String data) {
		return eRepository.ListarExameTudo(id, exame, data);
	}
}  
