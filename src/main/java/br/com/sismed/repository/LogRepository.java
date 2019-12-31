package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sismed.domain.Funcionario;
import br.com.sismed.domain.Login;

public interface LogRepository extends JpaRepository<Funcionario, Long>{

	Login findByCpf(String cpf);
}
