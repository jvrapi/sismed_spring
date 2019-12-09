package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.LabTConv;

public interface LabTConvRepository extends JpaRepository<LabTConv, Long>{
	
	@Modifying
	@Query(value = "DELETE FROM sismed_laboratorio_tconvenio WHERE sismed_tipo_convenio_id = :id AND sismed_laboratorio_id = :labId", nativeQuery = true)
	void deleteTConvLab(Long id, Long labId);
	
}
