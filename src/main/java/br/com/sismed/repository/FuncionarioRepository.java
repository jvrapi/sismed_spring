package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query(value = "SELECT * FROM sismed_funcionario WHERE id = :dado", nativeQuery = true)
	Funcionario ListarFuncionarioId(String dado);
	
	@Query(value = "SELECT * FROM sismed_funcionario WHERE nome LIKE %:dado%", nativeQuery = true)
	List<Funcionario> ListarFuncionarioNome(String dado);

	@Query(value = "SELECT * FROM sismed_funcionario WHERE cpf LIKE %:dado%", nativeQuery = true)
	List<Funcionario> ListarFuncionarioCPF(String dado);

	@Query(value = "SELECT * FROM sismed_funcionario WHERE celular LIKE %:dado%", nativeQuery = true)
	List<Funcionario> ListarFuncionarioCelular(String dado);

	@Query(value = "SELECT * FROM sismed_funcionario WHERE crm LIKE %:dado%", nativeQuery = true)
	List<Funcionario> ListarFuncionarioCRM(String dado);
	
	@Query(value = "SELECT * FROM sismed_funcionario WHERE especialidade LIKE %:dado%", nativeQuery = true)
	List<Funcionario> ListarFuncionarioEspecialidade(String dado);
	
	@Query(value = "SELECT * FROM sismed_funcionario WHERE crm IS NOT NULL", nativeQuery = true)
	List<Funcionario> ListarMedicos();

	
	
	
	
	
}
