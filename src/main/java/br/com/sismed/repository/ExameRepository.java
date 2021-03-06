package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Exame;


public interface ExameRepository extends JpaRepository<Exame, Long> {

	@Query(value = "SELECT * FROM sismed_exame WHERE id = :dado", nativeQuery = true)
	Exame ListarExameId(String dado);
	
	@Query(value = "SELECT DISTINCT * FROM sismed_exame e JOIN sismed_paciente p ON e.paciente_id = p.id WHERE p.nome LIKE %:dado%", nativeQuery = true)
	List<Exame> ListarExamePaciente(String dado);
	
	@Query(value = "SELECT DISTINCT * FROM sismed_exame WHERE nome LIKE %:dado%", nativeQuery = true)
	List<Exame> ListarExameNome(String dado);

	@Query(value = "SELECT * FROM sismed_exame WHERE data_coleta LIKE %:dado%", nativeQuery = true)
	List<Exame> ListarExameDataColeta(String dado);
	
	@Query(value = "SELECT * FROM sismed_exame WHERE paciente_id = :id", nativeQuery = true)
	List<Exame> BuscarExamePacienteId(Long id);
	
	@Query(value = "SELECT * FROM sismed_exame WHERE nome = :exame", nativeQuery = true)
	List<Exame> BuscarExamePorNome(String exame);
	
	@Query(value = "SELECT * FROM sismed_exame WHERE data_coleta = :data", nativeQuery = true)
	List<Exame> ListarExameData(String data);
	
	@Query(value = "SELECT * FROM sismed_exame WHERE paciente_id = :id && nome = :exame", nativeQuery = true)
	List<Exame> ListarExamePacienteExame(Long id, String exame);
			
	@Query(value = "SELECT * FROM sismed_exame WHERE paciente_id = :id && data_coleta = :data", nativeQuery = true)
	List<Exame> ListarExamePacienteData(Long id, String data);
	
	@Query(value = "SELECT * FROM sismed_exame WHERE nome = :exame && data_coleta = :data", nativeQuery = true)
	List<Exame> ListarExameExameData(String exame, String data);
	
	@Query(value = "SELECT * FROM sismed_exame WHERE paciente_id = :id && nome = :exame && data_coleta = :data", nativeQuery = true)
	List<Exame> ListarExameTudo(Long id, String exame, String data);
}