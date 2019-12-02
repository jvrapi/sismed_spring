package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Exame;


public interface ExameRepository extends JpaRepository<Exame, Long> {

	@Query(value = "SELECT * FROM sismed_exame WHERE id = :dado", nativeQuery = true)
	Exame ListarExameId(String dado);
	
	@Query(value = "SELECT * FROM sismed_exame e JOIN sismed_paciente p ON e.paciente_id = p.id WHERE nome LIKE %:dado%", nativeQuery = true)
	List<Exame> ListarExamePaciente(String dado);
	
	@Query(value = "SELECT * FROM sismed_exame WHERE nome LIKE %:dado%", nativeQuery = true)
	List<Exame> ListarExameNome(String dado);

	@Query(value = "SELECT * FROM sismed_exame WHERE data_coleta LIKE %:dado%", nativeQuery = true)
	List<Exame> ListarExameDataColeta(String dado);
}