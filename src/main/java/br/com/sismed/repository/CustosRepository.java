package br.com.sismed.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Custos;

public interface CustosRepository extends JpaRepository<Custos, Long>{

	


	@Query(value="SELECT *  FROM sismed_custos WHERE paciente = :paciente", nativeQuery=true)
	List<Custos> buscarPorPaciente(Long paciente);
	
	@Query(value="SELECT * FROM sismed_custos  WHERE convenio = :convenio", nativeQuery=true)
	List<Custos> buscarPorConvenio(Long convenio);
	
	@Query(value="SELECT *  FROM sismed_custos  WHERE funcionario = :funcionario", nativeQuery=true)
	List<Custos> buscarPorFuncionario(Long funcionario);
	
	
	@Query(value="SELECT * FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim", nativeQuery=true)
	List<Custos> buscarPorDatas(String dataInicio, String dataFim);

	
	@Query(value="SELECT * FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim AND paciente = :paciente", nativeQuery=true)
	List<Custos> PacientePeriodo(Long paciente, String dataInicio, String dataFim);
	
	@Query(value="SELECT *FROM sismed_custos ORDER BY convenio", nativeQuery=true)
	List<Custos> buscarTodosConvenios();
	
	@Query(value="SELECT * FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim AND convenio = :convenio", nativeQuery=true)
	List<Custos> ConvenioPeriodo(Long convenio, String dataInicio, String dataFim);
	
	@Query(value="SELECT  sum(valor) as valor  FROM sismed_custos  WHERE paciente = :paciente ", nativeQuery=true)
	BigDecimal buscarReceitaPorPaciente(Long paciente);
	
	
	
	@Query(value="SELECT  sum(valor) as valor  FROM sismed_custos  WHERE convenio = :convenio ", nativeQuery=true)
	BigDecimal buscarReceitaPorConveino(Long convenio);


	@Query(value="SELECT sum(valor) FROM sismed_custos WHERE funcionario = :funcionario", nativeQuery=true)
	BigDecimal buscarReceitaPorFuncionario(Long funcionario);

	
	@Query(value="SELECT SUM(valor) FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim", nativeQuery=true)
	BigDecimal buscarReceitaPorDatas(String dataInicio, String dataFim);

	
	@Query(value="SELECT SUM(valor) FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim AND paciente = :paciente", nativeQuery=true)
	BigDecimal ReceitaPacientePeriodo(Long paciente, String dataInicio, String dataFim);
	
	@Query(value="SELECT SUM(valor) FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim AND funcionario = :funcionario", nativeQuery=true)
	BigDecimal ReceitaFuncionarioPeriodo(Long funcionario, String dataInicio, String dataFim);

	@Query(value="SELECT SUM(valor)  FROM sismed_custos ", nativeQuery=true)
	BigDecimal receitaTodosConvenios();

	@Query(value="SELECT SUM(valor) FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim AND convenio = :convenio", nativeQuery=true)
	BigDecimal receitaConvenioPeriodo(Long convenio, String dataInicio, String dataFim);

	@Query(value="SELECT * FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim ", nativeQuery=true)
	List<Custos> TodosConvenioPeriodo(String dataInicio, String dataFim);

	@Query(value="SELECT SUM(valor) FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim ", nativeQuery=true)
	BigDecimal ReceitaTodosConvenioPeriodo(String dataInicio, String dataFim);

	@Query(value="SELECT * FROM sismed_custos WHERE data BETWEEN  :dataInicio AND :dataFim AND funcionario = :funcionario", nativeQuery=true)
	List<Custos> FuncionarioPeriodo(Long funcionario, String dataInicio, String dataFim);


	

	
	
	
	
	
	
}
