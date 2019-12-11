package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import br.com.sismed.domain.Convenio;


public interface ConvenioRepository extends JpaRepository<Convenio, Long>{

	@Query(value = "SELECT * FROM sismed_convenio c WHERE c.id =  :id", nativeQuery=true)
	List<Convenio> ListaComboBox(Long id);
	
	@Query(value = "SELECT * FROM sismed_convenio c WHERE c.nome  LIKE %:dado%", nativeQuery=true)
	List<Convenio> ListarPorNome(String dado);
	
	@Query(value = "SELECT * FROM sismed_convenio c WHERE c.cnpj  LIKE %:dado%", nativeQuery=true)
	List<Convenio> ListarPorCNPJ(String dado);
	
	@Query(value = "SELECT * FROM sismed_convenio c WHERE c.ans  LIKE %:dado%", nativeQuery=true)
	List<Convenio> ListarPorANS(String dado);
	
	@Query(value = "SELECT DISTINCT c.* FROM sismed_convenio c JOIN sismed_tipo_convenio tc ON c.id = tc.convenio_id "
			+ "JOIN sismed_laboratorio_tconvenio lt ON lt.sismed_tipo_convenio_id = tc.id WHERE lt.sismed_laboratorio_id = :id", nativeQuery = true)
	List<Convenio> BuscarConvLab(Long id);
	
	@Query(value = "SELECT c.id, c.nome,c.data_adesao, c.cnpj, c.registro_ans, c.dados_bancarios " + 
			"FROM sismed_convenio c INNER JOIN sismed_tipo_convenio tc ON c.id = tc.convenio_id " + 
			"INNER JOIN sismed_funcionario_tconvenio ft ON ft.tipo_convenio_id = tc.id " + 
			"INNER JOIN sismed_funcionario f ON ft.funcionario_id = f.id " + 
			"WHERE f.id = :id " + 
			"GROUP BY c.id", nativeQuery = true)
	List<Convenio>funcionarioConvenios(Long id);
}
