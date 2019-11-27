package br.com.sismed.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.sismed.domain.Paciente;

public interface PacienteService {
	
	void salvar(Paciente paciente);
	void excluir(Long id);
	
	Paciente buscarporId(Long id);
	
	Page<Paciente> buscarTodos(Pageable pageable);
	List<Paciente> findAll();
	
	List<Paciente> ListarPacId(String dado);
	List<Paciente> ListarPacNome(String dado);
	List<Paciente> PesquisarCPF(String dado);
	List<Paciente> PesquisarTelefone(String dado);
}
