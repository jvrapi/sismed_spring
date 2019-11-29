package br.com.sismed.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.RegistroClinico;

public interface RClinicoRepository extends JpaRepository<RegistroClinico, Long> {

	@Query(value = "SELECT * FROM sismed_registro_clinico r JOIN sismed_agenda a ON r.paciente_id = a.paciente_id WHERE a.id = :id", nativeQuery = true)
	Page<RegistroClinico> ListarRegAgenda(Long id, Pageable pageable);
	
	@Query(value = "SELECT * FROM sismed_registro_clinico WHERE paciente_id = :id", nativeQuery = true)
	Page<RegistroClinico> ListarRegPac(Long id, Pageable pageable);
	
	@Query(value = "SELECT COUNT(id) FROM sismed_registro_clinico WHERE paciente_id = :id", nativeQuery = true)
	Long qntIds(Long id);
	
	@Query(value = "SELECT * FROM sismed_registro_clinico WHERE paciente_id = :id ORDER BY id DESC LIMIT 1", nativeQuery = true)
	RegistroClinico ListarRegPac2(Long id);
}