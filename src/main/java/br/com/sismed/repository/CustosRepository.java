package br.com.sismed.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Paciente;
import br.com.sismed.domain.Custos;


public interface CustosRepository extends JpaRepository<Custos, Long>{

	
	/* CONSULTAS DE PACIENTE*/
	@Query(value="SELECT  sum(valor) as valor " + 
			" FROM sismed_custos  " + 
			" WHERE prontuario = :paciente ", nativeQuery=true)
	BigDecimal buscarReceitaPorPaciente(Long paciente);
	
	
	@Query(value="SELECT * " + 
			" FROM sismed_custos  " + 
			" WHERE paciente = :paciente", nativeQuery=true)
	List<Custos> buscarPorPaciente(Long paciente);

	/* CONSULTAS DE CONVENIO*/
	@Query(value="SELECT * " + 
			" FROM sismed_custos  " + 
			" WHERE convenio = :convenio", nativeQuery=true)
	List<Custos> buscarPorConvenio(Long convenio);

	@Query(value="SELECT  sum(valor) as valor " + 
			" FROM sismed_custos  " + 
			" WHERE convenio = :convenio ", nativeQuery=true)
	BigDecimal buscarReceitaPorConveino(Long convenio);


	
	@Query(value="SELECT * " + 
			" FROM sismed_custos  " + 
			" WHERE funcionario = :funcionario", nativeQuery=true)
	List<Custos> buscarPorFuncionario(Long funcionario);

	@Query(value="SELECT sum(valor) " + 
			" FROM sismed_custos  " + 
			" WHERE funcionario = :funcionario", nativeQuery=true)
	BigDecimal buscarReceitaPorFuncionario(Long funcionario);

	@Query(value="SELECT * " + 
			"FROM sismed_custos  " + 
			"WHERE data BETWEEN  :dataInicio AND data :dataFim", nativeQuery=true)
	List<Custos> buscarPorDatas(String dataInicio, String dataFim);
	
}
