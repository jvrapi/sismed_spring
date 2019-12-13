package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sismed.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	@Query(value = "SELECT * FROM sismed_paciente WHERE id = :dado", nativeQuery = true)
	List<Paciente> ListarPacId(String dado); 
	
	@Query(value = "SELECT * FROM sismed_paciente WHERE nome LIKE %:dado%", nativeQuery = true)
	List<Paciente> ListarPacNome(String dado);
	
	@Query(value = "SELECT * FROM sismed_paciente WHERE cpf LIKE %:dado%", nativeQuery = true)
	List<Paciente> ListarPacCPF(String dado);
	
	@Query(value = "SELECT * FROM sismed_paciente WHERE telefone_fixo LIKE %:dado%", nativeQuery = true)
	List<Paciente> ListarPacTel(String dado);
	
	@Query(value = "SELECT * FROM sismed_paciente WHERE celular LIKE %:dado%", nativeQuery = true)
	List<Paciente> ListarPacCel(String dado);
	
	@Query(value = "SELECT DISTINCT p.id FROM sismed_paciente p JOIN sismed_registro_clinico r ON p.id = r.paciente_id ORDER BY p.nome ASC", nativeQuery = true)
	Page<Integer> ContaId(Pageable pageable);
	
	@Query(value = "SELECT AUTO_INCREMENT FROM information_schema.tables " + 
			" WHERE table_name = 'sismed_paciente' AND table_schema = 'tresta' ", nativeQuery = true)
	Long Ultimoid();
	
	 
}