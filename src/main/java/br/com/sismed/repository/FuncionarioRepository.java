package br.com.sismed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sismed.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query(value = "SELECT * FROM sismed_funcionario WHERE nome = :dado", nativeQuery = true)
	List<Funcionario> ListarFuncionarioNome(String dado);
}
