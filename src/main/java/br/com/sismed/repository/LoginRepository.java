package br.com.sismed.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.Login;



public interface LoginRepository extends JpaRepository<Login, Long>{

	@Query("select l from Login l where l.cpf like :cpf")
	Login findByCpf(@Param("cpf") String cpf);

	@Query("select l from Login l join l.funcionario_id f where l.cpf like :cpf and f.data_termino is null")
	Optional<Login> findByCpfEAtivo(@Param("cpf") String cpf);

	
	
}
