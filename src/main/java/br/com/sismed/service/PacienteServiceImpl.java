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
public class PacienteServiceImpl implements PacienteService{

	@Autowired 
	private PacienteRepository pRepository;
	
	@Override
	public void salvar(Paciente paciente) {
		pRepository.save(paciente);
	}

	@Override
	public void excluir(Long id) {
		pRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Paciente buscarporId(Long id) {
		return pRepository.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Paciente> buscarTodos(Pageable pageable) {
		return pRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		return pRepository.findAll();
	}
	
	@Override
	public List<Paciente> ListarPacId(String dado) {
		return pRepository.ListarPacId(dado);
	}
	
	@Override
	public List<Paciente> ListarPacNome(String dado) {
		return pRepository.ListarPacNome(dado);
	}

	@Override
	public List<Paciente> PesquisarCPF(String dado) {
		return pRepository.ListarPacCPF(dado);
	}

	@Override
	public List<Paciente> PesquisarTelefone(String dado) {
		return pRepository.ListarPacTel(dado);
	}
}