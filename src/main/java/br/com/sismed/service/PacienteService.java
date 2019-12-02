package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Paciente;
import br.com.sismed.repository.PacienteRepository;

@Repository
@Transactional(readOnly = false)
public class PacienteService{

	@Autowired 
	private PacienteRepository pRepository;
	
	
	public void salvar(Paciente paciente) {
		pRepository.save(paciente);
	}

	
	public void excluir(Long id) {
		pRepository.deleteById(id);
	}

	
	@Transactional(readOnly = true)
	public Paciente buscarporId(Long id) {
		return pRepository.getOne(id);
	}

	
	@Transactional(readOnly = true)
	public Page<Paciente> buscarTodos(Pageable pageable) {
		return pRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		return pRepository.findAll();
	}
	
	
	public List<Paciente> ListarPacId(String dado) {
		return pRepository.ListarPacId(dado);
	}
	
	
	public List<Paciente> ListarPacNome(String dado) {
		return pRepository.ListarPacNome(dado);
	}

	
	public List<Paciente> PesquisarCPF(String dado) {
		return pRepository.ListarPacCPF(dado);
	}

	
	public List<Paciente> PesquisarTelefone(String dado) {
		return pRepository.ListarPacTel(dado);
	}
	
	public List<Paciente> PesquisarCelular(String dado) {
		return pRepository.ListarPacCel(dado);
	}
	
	public Page<Integer> ContaId(Pageable pageable) {
		return pRepository.ContaId(pageable);
	}
}