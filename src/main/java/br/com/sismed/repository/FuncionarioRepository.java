package br.com.sismed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sismed.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
