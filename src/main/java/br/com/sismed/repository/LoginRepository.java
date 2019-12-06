package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.Login;



public interface LoginRepository extends JpaRepository<Login, Long>{

	@Query("select l from Login l where l.cpf like :cpf")
	Login findByCpf(@Param("cpf") String cpf);
}
