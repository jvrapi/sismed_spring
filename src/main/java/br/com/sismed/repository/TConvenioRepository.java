package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sismed.domain.TConvenio;

@Repository
public interface TConvenioRepository extends JpaRepository<TConvenio, Long>{
	
	@Query(value = "SELECT tc.id, tc.nome, tc.convenio_id FROM sismed_convenio c INNER JOIN sismed_tipo_convenio tc ON c.id = tc.convenio_id WHERE c.id = :id", nativeQuery = true)
	List<TConvenio> ListaComboxBox(Long id);
}
