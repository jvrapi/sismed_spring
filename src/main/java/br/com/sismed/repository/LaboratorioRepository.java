package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>{

	@Query(value = "SELECT * FROM sismed_laboratorio WHERE id = :dado", nativeQuery = true)
	Laboratorio ListarLaboratorioId(String dado);
	
	@Query(value = "SELECT * FROM sismed_laboratorio WHERE nome LIKE %:dado%", nativeQuery = true)
	List<Laboratorio> ListarLaboratorioNome(String dado);

	@Query(value = "SELECT * FROM sismed_laboratorio WHERE telefone_fixo LIKE %:dado%", nativeQuery = true)
	List<Laboratorio> ListarLaboratorioTelefone(String dado);
	
	@Query(value = "SELECT * FROM sismed_laboratorio l JOIN sismed_endereco e ON l.endereco_id = e.id WHERE bairro LIKE %:dado%", nativeQuery = true)
	List<Laboratorio> ListarLaboratorioBairro(String dado);
	
	@Query(value = "SELECT l.* FROM sismed_laboratorio l JOIN sismed_laboratorio_tconvenio ltc ON l.id = ltc.sismed_laboratorio_id WHERE ltc.sismed_tipo_convenio_id = :id", nativeQuery = true)
	List<Laboratorio> ListarLabTConv(Long id);
}
