package br.com.sismed.service;





import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.Custos;

import br.com.sismed.repository.CustosRepository;


@Service 
public class CustosService {
	
	@Autowired
	private CustosRepository repository;
	
	@Transactional(readOnly = false)
	public void salvar(Custos c) {
		repository.save(c);
	}

	@Transactional(readOnly = true)
	public BigDecimal buscarReceitaPorPaciente(Long paciente) {
		return repository.buscarReceitaPorPaciente(paciente);
		
	}
	
	@Transactional(readOnly = true)
	public BigDecimal BuscarReceitaPorConvenio(Long convenio) {
		return repository.buscarReceitaPorConveino(convenio);
		
	}
	
	@Transactional(readOnly = true)
	public BigDecimal buscarReceitaPorFuncionario(Long funcionario) {
		return repository.buscarReceitaPorFuncionario(funcionario);
	}

	@Transactional(readOnly = true)
	public BigDecimal buscarReceitaPorDatas(String dataInicio, String dataFim) {
		return repository.buscarReceitaPorDatas(dataInicio, dataFim);
	}

	@Transactional(readOnly = true)
	public BigDecimal ReceitaPacientePeriodo(Long paciente, String dataInicio, String dataFim) {
		return repository.ReceitaPacientePeriodo(paciente,dataInicio,dataFim);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal ReceitaConvenioPeriodo(Long paciente, String dataInicio, String dataFim) {
		return repository.receitaConvenioPeriodo(paciente, dataInicio, dataFim);
	}
	
	@Transactional(readOnly = true)
	public BigDecimal ReceitaFuncionarioPeriodo(Long funcionario, String dataInicio, String dataFim) {
		return repository.ReceitaFuncionarioPeriodo(funcionario,dataInicio, dataFim);
	}
	
	@Transactional(readOnly = true)
	public List<Custos> buscarTodosConvenios() {
		return repository.buscarTodosConvenios();
		
	}
	
	@Transactional(readOnly = true)
	public BigDecimal receitaTodosConvenios(){
		return repository.receitaTodosConvenios();
		
	}

	
	
	@Transactional(readOnly = true)
	public List<Custos> PacientePeriodo(Long paciente, String dataInicio, String dataFim) {
		return repository.PacientePeriodo(paciente, dataInicio, dataFim);
	}
	
	@Transactional(readOnly = true)
	public List<Custos> buscarPorDatas(String dataInicio, String dataFim) {
		return repository.buscarPorDatas(dataInicio, dataFim);
	}
	
	@Transactional(readOnly = true)
	public List<Custos> buscarPorFuncionario(Long funcionario){
		return repository.buscarPorFuncionario(funcionario);
	}

	@Transactional(readOnly = true)
	public List<Custos> buscarPorConvenio(Long convenio) {
		return repository.buscarPorConvenio(convenio);
		
	}
	
	@Transactional(readOnly = true)
	public List<Custos> buscarPorPaciente(Long paciente) {
		return repository.buscarPorPaciente(paciente);
		
	}
	
	@Transactional(readOnly = true)
	public List<Custos> ConvenioPeriodo(Long convenio, String dataInicio, String dataFim) {
		return repository.ConvenioPeriodo(convenio, dataInicio, dataFim);
		
	}

	
	@Transactional(readOnly = true)
	public List<Custos> TodosConvenioPeriodo(String dataInicio, String dataFim) {
		return repository.TodosConvenioPeriodo(dataInicio, dataFim);
	}

	@Transactional(readOnly = true)
	public BigDecimal ReceitaTodosConvenioPeriodo(String dataInicio, String dataFim) {
		return repository.ReceitaTodosConvenioPeriodo(dataInicio, dataFim);
	}

	@Transactional(readOnly = true)
	public List<Custos> FuncionarioPeriodo(Long funcionario, String dataInicio, String dataFim) {
		return repository.FuncionarioPeriodo(funcionario,dataInicio, dataFim);
	}

	
	

	
}
