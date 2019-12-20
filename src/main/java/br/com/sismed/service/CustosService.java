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

	public BigDecimal buscarReceitaPorPaciente(Long paciente) {
		return repository.buscarReceitaPorPaciente(paciente);
		
	}

	public BigDecimal BuscarReceitaPorConvenio(Long convenio) {
		return repository.buscarReceitaPorConveino(convenio);
		
	}
	
	public BigDecimal buscarReceitaPorFuncionario(Long funcionario) {
		return repository.buscarReceitaPorFuncionario(funcionario);
	}

	public BigDecimal buscarReceitaPorDatas(String dataInicio, String dataFim) {
		return repository.buscarReceitaPorDatas(dataInicio, dataFim);
	}

	public BigDecimal ReceitaPacientePeriodo(Long paciente, String dataInicio, String dataFim) {
		return repository.ReceitaPacientePeriodo(paciente,dataInicio,dataFim);
	}
	
	

	public List<Custos> buscarTodosConvenios() {
		return repository.buscarTodosConvenios();
	}
	
	public List<BigDecimal> receitaTodosConvenios(){
		return repository.receitaTodosConvenios();
		
	}

	
	
	
	public List<Custos> PacientePeriodo(Long paciente, String dataInicio, String dataFim) {
		return repository.PacientePeriodo(paciente, dataInicio, dataFim);
	}
	
	public List<Custos> buscarPorDatas(String dataInicio, String dataFim) {
		return repository.buscarPorDatas(dataInicio, dataFim);
	}
	
	public List<Custos> buscarPorFuncionario(Long funcionario){
		return repository.buscarPorFuncionario(funcionario);
	}

	public List<Custos> buscarPorConvenio(Long convenio) {
		return repository.buscarPorConvenio(convenio);
		
	}
	
	public List<Custos> buscarPorPaciente(Long paciente) {
		return repository.buscarPorPaciente(paciente);
		
	}
	
	public List<Custos> ConvenioPeriodo(Long convenio, String dataInicio, String dataFim) {
		return repository.ConvenioPeriodo(convenio, dataInicio, dataFim);
		
	}

	public BigDecimal ReceitaConvenioPeriodo(Long paciente, String dataInicio, String dataFim) {
		return repository.receitaConvenioPeriodo(paciente, dataInicio, dataFim);
	}

	public List<Custos> TodosConvenioPeriodo(String dataInicio, String dataFim) {
		return repository.TodosConvenioPeriodo(dataInicio, dataFim);
	}

	public List<BigDecimal> ReceitaTodosConvenioPeriodo(String dataInicio, String dataFim) {
		return repository.ReceitaTodosConvenioPeriodo(dataInicio, dataFim);
	}

	
}
