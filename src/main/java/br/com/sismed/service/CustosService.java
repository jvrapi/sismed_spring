package br.com.sismed.service;





import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Custos;
import br.com.sismed.domain.Paciente;
import br.com.sismed.repository.CustosRepository;

@Service 
public class CustosService {
	
	@Autowired
	private CustosRepository repository;
	
	@Transactional(readOnly = false)
	public void salvar(Custos c) {
		repository.save(c);
	}

	public List<Custos> buscarPorPaciente(Long paciente) {
		return repository.buscarPorPaciente(paciente);
		
	}
	
	public BigDecimal buscarReceitaPorPaciente(Long paciente) {
		return repository.buscarReceitaPorPaciente(paciente);
		
	}

	public List<Custos> buscarPorConvenio(Long convenio) {
		return repository.buscarPorConvenio(convenio);
		
	}

	public BigDecimal BuscarReceitaPorConvenio(Long convenio) {
		return repository.buscarReceitaPorConveino(convenio);
		
	}
	
	public List<Custos> buscarPorFuncionario(Long funcionario){
		return repository.buscarPorFuncionario(funcionario);
	}
	
	public BigDecimal buscarReceitaPorFuncionario(Long funcionario) {
		return repository.buscarReceitaPorFuncionario(funcionario);
	}

	public List<Custos> buscarPorDatas(String dataInicio, String dataFim) {
		return repository.buscarPorDatas(dataInicio, dataFim);
	}
	
	
	

}
