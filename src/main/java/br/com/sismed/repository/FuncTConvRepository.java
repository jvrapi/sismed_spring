package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sismed.domain.FuncTConv;

@Repository
public interface FuncTConvRepository extends JpaRepository<FuncTConv, Long>{

	@Modifying
	@Query(value = "DELETE FROM sismed_funcionario_tconvenio WHERE funcionario_id = :funcId AND tipo_convenio_id = :id", nativeQuery = true)
	void deleteTConvFunc(Long id, Long funcId);
	
}
