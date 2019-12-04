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
}
